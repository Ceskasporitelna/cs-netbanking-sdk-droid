package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.Sort;
import cz.csas.cscore.webapi.SortDirection;
import cz.csas.netbanking.Format;
import cz.csas.netbanking.Language;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.Periodicity;
import cz.csas.netbanking.Statement;
import cz.csas.netbanking.StatementsListResponse;
import cz.csas.netbanking.StatementsParameters;
import cz.csas.netbanking.StatementsSortableFields;

import static junit.framework.Assert.assertEquals;

/**
 * The type Account with id sub account statements test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.04.16.
 */
public class AccountWithIdSubAccountStatementsListTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "accounts.withId.subAccounts.withId.statements.list";
    private CountDownLatch mStatementsSignal;
    private StatementsListResponse mStatementsListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mStatementsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account with id subaccount statements list.
     */
    @Test
    public void testAccountWithIdSubAccountStatementsList() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        String subAccountId = "0D5F82464A77DF093858A8A5B938BEE410B4409C";
        StatementsParameters parameters = new StatementsParameters(null,
                Sort.by(StatementsSortableFields.STATEMENT_DATE, SortDirection.ASCENDING));

        mNetbankingClient.getAccountsResource().withId(accountId).getSubAccountsResource().withId(subAccountId)
                .getStatementsResource().list(parameters, new CallbackWebApi<StatementsListResponse>() {
            @Override
            public void success(StatementsListResponse statementsListResponse) {
                mStatementsListResponse = statementsListResponse;
                mStatementsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mStatementsSignal.countDown();
            }
        });

        try {
            mStatementsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(0), mStatementsListResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mStatementsListResponse.getPageCount());
        assertEquals(Long.valueOf(2), mStatementsListResponse.getPageSize());
        assertEquals(Long.valueOf(1), mStatementsListResponse.getNextPage());

        List<Statement> statements = mStatementsListResponse.getStatements();
        assertEquals(2, statements.size());

        for (int i = 0; i < statements.size(); ++i) {
            Statement statement = statements.get(i);
            switch (i) {
                case 0:
                    assertEquals("201302520130621161819", statement.getId());
                    assertEquals(Integer.valueOf(25), statement.getNumber());
                    assertEquals(TimeUtils.getISO8601Date("2013-06-21T14:18:19Z"), statement.getStatementDate());
                    assertEquals(Periodicity.MONTHLY, statement.getPeriodicity());
                    assertEquals(Format.PDF_A4, statement.getFormat());
                    assertEquals(Language.CS, statement.getLanguage());
                    assertEquals(Integer.valueOf(1), statement.getCzFileOrderNumber());
                    assertEquals(Integer.valueOf(1), statement.getCzFileTotalNumber());
                    break;
                case 1:
                    assertEquals("201302524845621161819", statement.getId());
                    assertEquals(Integer.valueOf(19), statement.getNumber());
                    assertEquals(TimeUtils.getISO8601Date("2014-05-11T14:12:19Z"), statement.getStatementDate());
                    assertEquals(Periodicity.DAILY, statement.getPeriodicity());
                    assertEquals(Format.PDF_A4, statement.getFormat());
                    assertEquals(Language.CS, statement.getLanguage());
                    assertEquals(Integer.valueOf(1), statement.getCzFileOrderNumber());
                    assertEquals(Integer.valueOf(2), statement.getCzFileTotalNumber());
                    break;
            }
        }
    }

}

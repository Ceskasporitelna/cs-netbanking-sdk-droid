package cz.csas.netbanking.cards;

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
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.Format;
import cz.csas.netbanking.Language;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.Periodicity;
import cz.csas.netbanking.Statement;
import cz.csas.netbanking.StatementsListResponse;
import cz.csas.netbanking.StatementsParameters;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardAccountsStatementsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "cards.withId.accounts.withId.statements.list.page1";
    private CountDownLatch mCardsSignal;
    private StatementsListResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mCardsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testCardAccountsStatementsListPage1() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";

        StatementsParameters parameters = new StatementsParameters(new Pagination(1,1), null);

        mNetbankingClient.getCardsResource().withId(cardId).getAccountsResource().withId(accountId)
                .getStatements().list(parameters, new CallbackWebApi<StatementsListResponse>() {
            @Override
            public void success(StatementsListResponse statementsListResponse) {
                mResponse = statementsListResponse;
                mCardsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCardsSignal.countDown();
            }
        });

        try {
            mCardsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());

        List<Statement> statements = mResponse.getStatements();
        assertEquals(1, statements.size());

        Statement statement = statements.get(0);
        assertEquals("96029392819b0198", statement.getId());
        assertEquals(Integer.valueOf(8), statement.getNumber());
        assertEquals(Periodicity.MONTHLY, statement.getPeriodicity());
        assertEquals(Format.PDF_A4, statement.getFormat());
        assertEquals(Language.CS, statement.getLanguage());
        assertEquals(TimeUtils.getISO8601Date("2016-01-29T00:00:00+01:00"), statement.getStatementDate());
        assertEquals(Integer.valueOf(1), statement.getCzFileTotalNumber());
        assertEquals(Integer.valueOf(0), statement.getCzFileOrderNumber());
    }

}

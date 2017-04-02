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
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.Sort;
import cz.csas.cscore.webapi.SortDirection;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.orders.Symbols;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class DirectDebitsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.directDebts.list.page1";
    private CountDownLatch mCountDownLatch;
    private DirectDebitsListResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testDirectDebitsPage1List() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";
        DirectDebitsParameters parameters = new DirectDebitsParameters(new Pagination(1, 2),
                Sort.by(DirectDebitsSortableFields.PERIOD_CYCLE, SortDirection.DESCENDING));
        mNetbankingClient.getAccountsResource().withId(accountId).getDirectDebitsResource().list(parameters, new CallbackWebApi<DirectDebitsListResponse>() {
            @Override
            public void success(DirectDebitsListResponse standingOrdersListResponse) {
                mResponse = standingOrdersListResponse;
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());

        List<DirectDebit> directDebits = mResponse.getDirectDebits();
        assertEquals(1, directDebits.size());

        DirectDebit order = directDebits.get(0);
        AccountNumber receiver = order.getReceiver();
        Symbols symbols = order.getSymbols();
        Amount limitSum = order.getLimitSum();

        assertEquals("4", order.getNumber());
        assertEquals("101082201", receiver.getNumber());
        assertEquals("0800", receiver.getBankCode());
        assertEquals("CZ", receiver.getCountryCode());

        assertEquals(DirectDebitType.SIPO, order.getType());
        assertEquals(Integer.valueOf(1), order.getPeriodicity());
        assertEquals(PeriodCycle.MONTHLY, order.getPeriodCycle());

        assertEquals(Long.valueOf(99999999900l), limitSum.getValue());
        assertEquals(Integer.valueOf(2), limitSum.getPrecision());
        assertEquals("CZK", limitSum.getCurrency());

        assertEquals(TimeUtils.getPlainDate("2013-01-08"), order.getStartDate());

        assertEquals("8009710218", symbols.getVariableSymbol());
        assertEquals("0", symbols.getSpecificSymbol());

        assertEquals(Integer.valueOf(1), order.getVersionId());
        assertEquals(TimeUtils.getPlainDate("2013-01-08"), order.getVersionValidityDate());
    }
}

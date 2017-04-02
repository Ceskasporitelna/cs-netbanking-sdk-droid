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
public class DirectDebitsListPage0Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.directDebts.list.page0";
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
    public void testDirectDebitsPage0List() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";
        DirectDebitsParameters parameters = new DirectDebitsParameters(new Pagination(0,2),
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

        assertEquals(Long.valueOf(0), mResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(2), mResponse.getPageSize());
        assertEquals(Long.valueOf(1), mResponse.getNextPage());

        List<DirectDebit> directDebits = mResponse.getDirectDebits();
        assertEquals(2, directDebits.size());

        for(int i=0; i<directDebits.size(); ++i) {
            DirectDebit order = directDebits.get(i);
            AccountNumber receiver = order.getReceiver();
            Symbols symbols = order.getSymbols();
            Amount limitSum = order.getLimitSum();
            switch (i) {
                case 0:
                    assertEquals("2", order.getNumber());
                    assertEquals("428602109", receiver.getNumber());
                    assertEquals("0800", receiver.getBankCode());
                    assertEquals("CZ", receiver.getCountryCode());

                    assertEquals(DirectDebitType.DIRECT_DEBIT, order.getType());
                    assertEquals(Integer.valueOf(1), order.getPeriodicity());
                    assertEquals(PeriodCycle.MONTHLY, order.getPeriodCycle());

                    assertEquals(Long.valueOf(99999999900l), limitSum.getValue());
                    assertEquals(Integer.valueOf(2), limitSum.getPrecision());
                    assertEquals("CZK", limitSum.getCurrency());

                    assertEquals(TimeUtils.getPlainDate("2012-11-26"), order.getStartDate());

                    assertEquals("0", symbols.getVariableSymbol());
                    assertEquals("0", symbols.getSpecificSymbol());

                    assertEquals(Integer.valueOf(1), order.getVersionId());
                    assertEquals(TimeUtils.getPlainDate("2012-11-26"), order.getVersionValidityDate());
                    assertEquals("Vrba Aleš", order.getReceiverName());
                    break;

                case 1:
                    assertEquals("3", order.getNumber());
                    assertEquals("1330052", receiver.getNumber());
                    assertEquals("0800", receiver.getBankCode());
                    assertEquals("CZ", receiver.getCountryCode());

                    assertEquals(DirectDebitType.DIRECT_DEBIT, order.getType());
                    assertEquals(Integer.valueOf(12), order.getPeriodicity());
                    assertEquals(PeriodCycle.MONTHLY, order.getPeriodCycle());

                    assertEquals(Long.valueOf(88400), limitSum.getValue());
                    assertEquals(Integer.valueOf(2), limitSum.getPrecision());
                    assertEquals("CZK", limitSum.getCurrency());

                    assertEquals(TimeUtils.getPlainDate("2012-11-30"), order.getStartDate());

                    assertEquals("0", symbols.getVariableSymbol());
                    assertEquals("0", symbols.getSpecificSymbol());

                    assertEquals(Integer.valueOf(1), order.getVersionId());
                    assertEquals(TimeUtils.getPlainDate("2012-11-30"), order.getVersionValidityDate());
                    break;
            }
        }
    }
}

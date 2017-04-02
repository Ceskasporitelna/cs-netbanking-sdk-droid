package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.Arrays;
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
public class StandingOrdersListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.standingOrders.list.page1";
    private CountDownLatch mCountDownLatch;
    private StandingOrdersListResponse mResponse;

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
    public void testStandingOrdersPage1List() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";
        StandingOrdersParameters parameters = new StandingOrdersParameters(new Pagination(1, 2),
                Sort.by(StandingOrdersSortableFields.NEXT_EXECUTION_DATE, SortDirection.DESCENDING));
        mNetbankingClient.getAccountsResource().withId(accountId).getStandingOrdersResource().list(parameters, new CallbackWebApi<StandingOrdersListResponse>() {
            @Override
            public void success(StandingOrdersListResponse standingOrdersListResponse) {
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
        assertEquals(Long.valueOf(3), mResponse.getPageCount());
        assertEquals(Long.valueOf(2), mResponse.getPageSize());
        assertEquals(Long.valueOf(2), mResponse.getNextPage());

        List<StandingOrder> standingOrders = mResponse.getStandingOrders();
        assertEquals(2, standingOrders.size());

        for(int i=0; i<standingOrders.size(); ++i) {
            StandingOrder order = standingOrders.get(i);
            AccountNumber receiver = order.getReceiver();
            Amount amount = order.getAmount();
            Symbols symbols = order.getSymbols();
            switch (i) {
                case 0:
                    assertEquals("3", order.getNumber());
                    assertEquals(StandingOrderType.STANDING_ORDER, order.getType());
                    assertEquals("OK", order.getStatus());

                    assertEquals("35-2001269369", receiver.getNumber());
                    assertEquals("0800", receiver.getBankCode());
                    assertEquals("CZ", receiver.getCountryCode());
                    assertEquals("CZ7908000000352001269369", receiver.getCzIban());
                    assertEquals("pravidelný nákupPF-8846245-1", order.getSenderReference());

                    assertEquals(Long.valueOf(50000), amount.getValue());
                    assertEquals(Integer.valueOf(2), amount.getPrecision());
                    assertEquals("CZK", amount.getCurrency());

                    assertEquals(TimeUtils.getISO8601Date("2014-04-24T00:00:00+02:00"), order.getStartDate());
                    assertEquals(TimeUtils.getPlainDate("2016-06-15"), order.getNextExecutionDate());
                    assertEquals(TimeUtils.getPlainDate("2016-06-15"), order.getRealExecutionDate());
                    assertEquals(ExecutionDueMode.DUE_DAY_OF_MONTH, order.getExecutionDueMode());
                    assertEquals(ExecutionMode.UNTIL_CANCELLATION, order.getExecutionMode());
                    assertEquals(Integer.valueOf(15), order.getIntervalDueDay());

                    assertEquals("1034176627", symbols.getVariableSymbol());

                    assertEquals(Arrays.asList("deletable"), order.getFlags());
                    assertEquals(Arrays.asList(TimeUtils.getPlainDate("2016-06-15")), order.getScheduledExecutionDates());
                    break;

                case 1:
                    assertEquals("4", order.getNumber());
                    assertEquals(StandingOrderType.STANDING_ORDER, order.getType());
                    assertEquals("OK", order.getStatus());

                    assertEquals("35-2001269369", receiver.getNumber());
                    assertEquals("0800", receiver.getBankCode());
                    assertEquals("CZ", receiver.getCountryCode());
                    assertEquals("CZ7908000000352001269369", receiver.getCzIban());
                    assertEquals("pravidelný nákupPF-10007570-1ISČS Konzervativní Mix FF", order.getSenderReference());

                    assertEquals(Long.valueOf(100000), amount.getValue());
                    assertEquals(Integer.valueOf(2), amount.getPrecision());
                    assertEquals("CZK", amount.getCurrency());

                    assertEquals(TimeUtils.getISO8601Date("2015-07-31T00:00:00+02:00"), order.getStartDate());
                    assertEquals(TimeUtils.getPlainDate("2016-06-15"), order.getNextExecutionDate());
                    assertEquals(TimeUtils.getPlainDate("2016-06-15"), order.getRealExecutionDate());
                    assertEquals(ExecutionDueMode.DUE_DAY_OF_MONTH, order.getExecutionDueMode());
                    assertEquals(ExecutionMode.UNTIL_CANCELLATION, order.getExecutionMode());
                    assertEquals(Integer.valueOf(15), order.getIntervalDueDay());

                    assertEquals(Arrays.asList("deletable"), order.getFlags());
                    assertEquals(Arrays.asList(TimeUtils.getPlainDate("2016-06-15")), order.getScheduledExecutionDates());
                    break;
            }
        }
    }
}

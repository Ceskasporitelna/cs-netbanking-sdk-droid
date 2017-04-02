package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.orders.Symbols;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class StandingOrdersWithIdDeleteTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.standingOrders.withId.delete";
    private CountDownLatch mCountDownLatch;
    private StandingOrderResponse mResponse;

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
    public void testStandingOrderWithIdDelete() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";
        String orderId = "1";

        mNetbankingClient.getAccountsResource().withId(accountId).getStandingOrdersResource()
                .withId(orderId).delete(new CallbackWebApi<StandingOrderResponse>() {
            @Override
            public void success(StandingOrderResponse standingOrder) {
                mResponse = standingOrder;
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

        StandingOrderResponse order = mResponse;
        AccountNumber receiver = order.getReceiver();
        Amount amount = order.getAmount();
        Symbols symbols = order.getSymbols();

        assertEquals("1", order.getNumber());
        assertEquals(StandingOrderType.STANDING_ORDER, order.getType());
        assertEquals("OK", order.getStatus());
        assertEquals("nájemné", order.getAlias());

        assertEquals("174748262", receiver.getNumber());
        assertEquals("0300", receiver.getBankCode());
        assertEquals("CZ", receiver.getCountryCode());
        assertEquals("CZ6703000000000174748262", receiver.getCzIban());

        assertEquals(Long.valueOf(235000), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());

        assertEquals(TimeUtils.getISO8601Date("2013-01-09T00:00:00+01:00"), order.getStartDate());
        assertEquals(TimeUtils.getPlainDate("2016-06-17"), order.getNextExecutionDate());
        assertEquals(TimeUtils.getPlainDate("2016-06-17"), order.getRealExecutionDate());
        assertEquals(ExecutionDueMode.DUE_DAY_OF_MONTH, order.getExecutionDueMode());
        assertEquals(ExecutionMode.UNTIL_CANCELLATION, order.getExecutionMode());
        assertEquals(Integer.valueOf(17), order.getIntervalDueDay());

        assertEquals("8840709604", symbols.getVariableSymbol());
        assertEquals("311013", symbols.getSpecificSymbol());

        assertEquals(Arrays.asList("deletable"), order.getFlags());
        assertEquals(Arrays.asList(TimeUtils.getPlainDate("2016-06-17")), order.getScheduledExecutionDates());

        assertEquals(SigningState.OPEN, order.signing().getSigningState());
        assertEquals("160526104016300", order.signing().getSignId());
    }
}


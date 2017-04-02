package cz.csas.netbanking.accounts;

import org.junit.Test;

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
public class StandingOrdersCreateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.standingOrders.create";
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
    public void testStandingOrderCreate() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";

        StandingOrderCreateRequest request = new StandingOrderCreateRequest.Builder()
                .setType(StandingOrderType.STANDING_ORDER)
                .setAlias("Monthly standing order executed on the last day of month")
                .setReceiverName("Name of the receiver")
                .setReceiver(new AccountNumber("188505042", "0300"))
                .setAmount(new Amount(30000l, 2, "CZK"))
                .setNextExecutionDate(TimeUtils.getPlainDate("2016-12-31"))
                .setExecutionMode(ExecutionMode.UNTIL_CANCELLATION)
                .setExecutionDueMode(ExecutionDueMode.DUE_LAST_DAY_OF_MONTH)
                .setExecutionInterval(ExecutionInterval.MONTHLY)
                .setSymbols(new Symbols("854259", "0305", "785421"))
                .build();

        mNetbankingClient.getAccountsResource().withId(accountId).getStandingOrdersResource()
                .create(request, new CallbackWebApi<StandingOrderResponse>() {
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

        assertEquals("160526104005956", order.getNumber());
        assertEquals(StandingOrderType.STANDING_ORDER, order.getType());
        assertEquals("OK", order.getStatus());
        assertEquals("Monthly standing order executed on the last day of month", order.getAlias());

        assertEquals("188505042", receiver.getNumber());
        assertEquals("0300", receiver.getBankCode());
        assertEquals("Name of the receiver", order.getReceiverName());

        assertEquals(Long.valueOf(30000), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());

        assertEquals(TimeUtils.getISO8601Date("2016-12-31T00:00:00+01:00"), order.getStartDate());
        assertEquals(TimeUtils.getPlainDate("2016-12-31"), order.getNextExecutionDate());
        assertEquals(ExecutionDueMode.DUE_LAST_DAY_OF_MONTH, order.getExecutionDueMode());
        assertEquals(ExecutionMode.UNTIL_CANCELLATION, order.getExecutionMode());

        assertEquals("854259", symbols.getVariableSymbol());
        assertEquals("0305", symbols.getConstantSymbol());
        assertEquals("785421", symbols.getSpecificSymbol());

        assertEquals(SigningState.OPEN, order.signing().getSigningState());
        assertEquals("160526104005956", order.signing().getSignId());

    }
}


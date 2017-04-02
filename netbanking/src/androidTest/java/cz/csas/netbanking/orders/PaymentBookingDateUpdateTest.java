package cz.csas.netbanking.orders;

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
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentBookingDateUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.bookingDate.update";
    private CountDownLatch mOrdersSignal;
    private PaymentBookingDateResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mOrdersSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testPaymentBookingDateUpdate() {

        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";
        AccountNumber receiver = new AccountNumber("123-123", "0100", "CZ", null, null);

        PaymentBookingDateRequest request = new PaymentBookingDateRequest(accountId, receiver, PaymentOrderPriority.STANDARD);
        mNetbankingClient.getOrdersResource().getPaymentsResource().getBookingDateResource().update(request, new CallbackWebApi<PaymentBookingDateResponse>() {
            @Override
            public void success(PaymentBookingDateResponse paymentBookingDateResponse) {
                mResponse = paymentBookingDateResponse;
                mOrdersSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mOrdersSignal.countDown();
            }
        });

        try {
            mOrdersSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(TimeUtils.getISO8601Date("2016-03-21T00:00:00+01:00"), mResponse.getBookingDate());
    }
}

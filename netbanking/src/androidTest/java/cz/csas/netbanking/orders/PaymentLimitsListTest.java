package cz.csas.netbanking.orders;

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentLimitsListTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "payments.limits.list";
    private CountDownLatch mOrdersSignal;
    private PaymentLimitsListResponse mPaymentLimitsListResponse;

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
    public void testPaymentsLimits() {

        mNetbankingClient.getOrdersResource().getPaymentsResource().getLimitsResource().list(new CallbackWebApi<PaymentLimitsListResponse>() {
            @Override
            public void success(PaymentLimitsListResponse paymentLimitsListResponse) {
                mPaymentLimitsListResponse = paymentLimitsListResponse;
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

        List<PaymentLimit> limits = mPaymentLimitsListResponse.getPaymentLimits();
        assertEquals(2, limits.size());

        for (int i = 0; i < limits.size(); ++i) {
            PaymentLimit limit = limits.get(i);
            switch (i) {
                case 0:
                    assertEquals(AuthorizationType.TAC, limit.getAuthorizationType());
                    assertEquals(ChannelId.NET_BANKING, limit.getChannelId());
                    assertEquals(ApplicationId.GEORGE, limit.getApplicationId());
                    assertEquals(Long.valueOf(99999999999999L), limit.getRemainingAmount().getValue());
                    assertEquals(Integer.valueOf(2), limit.getRemainingAmount().getPrecision());
                    assertEquals("CZK", limit.getRemainingAmount().getCurrency());
                    break;
                case 1:
                    assertEquals(AuthorizationType.TAC, limit.getAuthorizationType());
                    assertEquals(ChannelId.NET_BANKING, limit.getChannelId());
                    assertEquals(ApplicationId.UNKNOWN, limit.getApplicationId());
                    assertEquals(Long.valueOf(99999999999999L), limit.getRemainingAmount().getValue());
                    assertEquals(Integer.valueOf(2), limit.getRemainingAmount().getPrecision());
                    assertEquals("CZK", limit.getRemainingAmount().getCurrency());
                    break;
            }
        }
    }
}

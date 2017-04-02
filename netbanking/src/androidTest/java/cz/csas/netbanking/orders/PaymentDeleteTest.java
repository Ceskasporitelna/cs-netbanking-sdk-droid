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
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentDeleteTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "payments.withId.delete";
    private CountDownLatch mOrdersSignal;


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
    public void testDeletePayment() {
        String orderId = "1023464260";
        mNetbankingClient.getOrdersResource().getPaymentsResource().withId(orderId).delete(new CallbackWebApi<WebApiEmptyResponse>() {
            @Override
            public void success(WebApiEmptyResponse removePaymentOrderResponse) {
                mOrdersSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                fail();
                mOrdersSignal.countDown();
            }
        });

        try {
            mOrdersSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}

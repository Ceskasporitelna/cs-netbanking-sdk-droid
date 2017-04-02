package cz.csas.netbanking.phoneNumbers;

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
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class PhoneNumbersDeleteTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "phoneBook.withId.delete";
    private CountDownLatch mCountDownLatch;

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
    public void testPhoneNumberUpdate() {

        mNetbankingClient.getPhoneNumbersResource().withId("2195").delete(new CallbackWebApi<WebApiEmptyResponse>() {
            @Override
            public void success(WebApiEmptyResponse phoneNumber) {
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                fail();
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package cz.csas.netbanking.authorizationToken;

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

import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class AuthorizationTokenInvalidateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "authorizationToken.delete";
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
    public void testAuthorizationTokenInvalidate() {
        mNetbankingClient.getAuthorizationTokenResource().delete(new CallbackWebApi<WebApiEmptyResponse>() {
            @Override
            public void success(WebApiEmptyResponse webApiEmptyResponse) {
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCountDownLatch.countDown();
                fail();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

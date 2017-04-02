package cz.csas.netbanking.messages;

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

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class MessagesWithIdUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "messages.withId.update";
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
    public void testMessageWithIdUpdate() {
        String id = "134625";
        MessageUpdateRequest request = new MessageUpdateRequest(true);
        mNetbankingClient.getMessagesResource().withId(id).update(request, new CallbackWebApi<WebApiEmptyResponse>() {
            @Override
            public void success(WebApiEmptyResponse updateMessageResponse) {
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

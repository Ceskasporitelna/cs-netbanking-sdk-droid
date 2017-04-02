package cz.csas.netbanking.messages;

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class MessagesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "messages.list";
    private CountDownLatch mCountDownLatch;
    private MessagesListResponse mResponse;

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
    public void testMessagesList() {
        mNetbankingClient.getMessagesResource().list(null, new CallbackWebApi<MessagesListResponse>() {
            @Override
            public void success(MessagesListResponse messagesListResponse) {
                mResponse = messagesListResponse;
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

        List<Message> messages = mResponse.getMessages();
        assertEquals(1, messages.size());

        Message message = messages.get(0);
        List<Attachment> attachments = message.getAttachments();

        assertEquals("134625", message.getId());
        assertEquals("WCM", message.getFrom());
        assertEquals("test attach", message.getSubject());
        assertEquals(TimeUtils.getISO8601Date("2016-04-08T09:20:32+02:00"), message.getDate());
        assertEquals(1, attachments.size());
        Attachment attachment = attachments.get(0);
        assertEquals("palec.png", attachment.getId());
        assertEquals("palec.png", attachment.getFileName());
        assertEquals(message.getId(), attachment.getMessage().getId());
    }
}

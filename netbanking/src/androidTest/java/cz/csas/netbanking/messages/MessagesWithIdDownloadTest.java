package cz.csas.netbanking.messages;

import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.WebApiStream;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class MessagesWithIdDownloadTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "messages.withId.attachments.withId.download";
    private CountDownLatch mCountDownLatch;
    private WebApiStream mWebApiStream;

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
    public void testMessageWithIdDownload() {
        String id = "1421721";
        String attachmentId = "palec.png";
        mNetbankingClient.getMessagesResource().withId(id).getAttachmentsResource()
                .withId(attachmentId).download(new CallbackWebApi<WebApiStream>() {
            @Override
            public void success(WebApiStream stream) {
                mWebApiStream = stream;
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

        try {
            Long size = (long) mWebApiStream.getInputStream().available();
            assertEquals("test-file.png", mWebApiStream.getFilename());
            assertEquals(mWebApiStream.getContentLength(), size);
            //TODO: this seems broken in the test case
            //assertEquals("application/octet-stream", mWebApiStream.getContentType().toLowerCase());
            //assertPng(mWebApiStream.getInputStream());
        } catch (IOException e) {
            fail();
        }

    }
}

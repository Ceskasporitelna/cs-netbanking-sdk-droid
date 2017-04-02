package cz.csas.netbanking.templates;

import org.junit.Test;

import java.util.HashMap;
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
 * @since 05.09.16.
 */
public class TemplatesWithIdGetTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "templates.withId.get";
    private CountDownLatch mCountDownLatch;
    private Template mResponse;

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
    public void testTemplateWithIdGet() {
        String id = "template_0-123-100";
        mNetbankingClient.getTemplatesResource().withId(id).get(new CallbackWebApi<Template>() {
            @Override
            public void success(Template template) {
                mResponse = template;
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

        Template template = mResponse;

        assertEquals("template_0-123-100", template.getId());
        assertEquals("Jan Novák", template.getName());
        assertEquals(OrderCategory.DOMESTIC, template.getOrderCategory());
        assertEquals("2326573123", template.getReceiver().getNumber());
        assertEquals("0800", template.getReceiver().getBankCode());
    }
}

package cz.csas.netbanking.templates;

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
 * @since 05.09.16.
 */
public class TemplatesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "templates.list";
    private CountDownLatch mCountDownLatch;
    private TemplatesListResponse mResponse;

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
    public void testTemplatesList() {
        mNetbankingClient.getTemplatesResource().list(null,
                new CallbackWebApi<TemplatesListResponse>() {
                    @Override
                    public void success(TemplatesListResponse templatesListResponse) {
                        mResponse = templatesListResponse;
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

        assertEquals(Long.valueOf(1), mResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(2), mResponse.getPageSize());
        assertEquals(Long.valueOf(1), mResponse.getNextPage());

        List<Template> templates = mResponse.getTemplates();
        assertEquals(2, templates.size());

        for (int i = 0; i < templates.size(); i++) {
            Template template = templates.get(i);
            switch (i) {
                case 0:
                    assertEquals("template_0-123-100", template.getId());
                    assertEquals("Jan Novák", template.getName());
                    assertEquals(OrderCategory.DOMESTIC, template.getOrderCategory());
                    assertEquals("2326573123", template.getReceiver().getNumber());
                    assertEquals("0800", template.getReceiver().getBankCode());
                    break;
                case 1:
                    assertEquals("template_0-124-100", template.getId());
                    assertEquals("Marek Nový", template.getName());
                    assertEquals(OrderCategory.DOMESTIC, template.getOrderCategory());
                    assertEquals("CZ3308000000001592286253", template.getReceiver().getIban());
                    assertEquals("GIBACZPX", template.getReceiver().getBic());
                    break;
            }
        }
    }
}

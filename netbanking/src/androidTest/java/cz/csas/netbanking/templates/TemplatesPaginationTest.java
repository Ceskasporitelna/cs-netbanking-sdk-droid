package cz.csas.netbanking.templates;

import org.junit.Test;

import java.util.Arrays;
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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.messages.Attachment;
import cz.csas.netbanking.messages.Message;
import cz.csas.netbanking.messages.MessagesListResponse;
import cz.csas.netbanking.messages.MessagesParameters;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class TemplatesPaginationTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "templates.list.pagination";
    private TemplatesListResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testMessagesPagination() {
        int pages[] = {0, 1, 0};
        for (int page : pages) {
            final CountDownLatch latch = new CountDownLatch(1);
            mResponse = null;

            mNetbankingClient.getTemplatesResource().list(new PaginatedParameters(new Pagination(page, 1)), new CallbackWebApi<TemplatesListResponse>() {
                @Override
                public void success(TemplatesListResponse templatesListResponse) {
                    mResponse = templatesListResponse;
                    latch.countDown();
                }

                @Override
                public void failure(CsSDKError error) {
                    latch.countDown();
                }
            });

            try {
                latch.await(20, TimeUnit.SECONDS); // wait for callback
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Template template = mResponse.getTemplates().get(0);

            switch (page) {
                case 0:
                    assertEquals(1, mResponse.getTemplates().size());
                    assertEquals(Long.valueOf(0), mResponse.getPageNumber());
                    assertEquals(Long.valueOf(2), mResponse.getPageCount());
                    assertEquals(Long.valueOf(1), mResponse.getPageSize());
                    assertEquals(Long.valueOf(1), mResponse.getNextPage());

                    assertEquals("template_0-123-100", template.getId());
                    assertEquals("Jan Novák", template.getName());
                    assertEquals(OrderCategory.DOMESTIC, template.getOrderCategory());
                    assertEquals("2326573123", template.getReceiver().getNumber());
                    assertEquals("0800", template.getReceiver().getBankCode());

                    break;

                case 1:
                    assertEquals(1, mResponse.getTemplates().size());
                    assertEquals(Long.valueOf(1), mResponse.getPageNumber());
                    assertEquals(Long.valueOf(2), mResponse.getPageCount());
                    assertEquals(Long.valueOf(1), mResponse.getPageSize());

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

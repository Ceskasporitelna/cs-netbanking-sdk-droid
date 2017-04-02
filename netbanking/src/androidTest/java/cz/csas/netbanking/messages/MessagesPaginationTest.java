package cz.csas.netbanking.messages;

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
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class MessagesPaginationTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "messages.pagination";
    private MessagesListResponse mResponse;

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
        for (int page: pages) {
            final CountDownLatch latch = new CountDownLatch(1);
            mResponse = null;

            mNetbankingClient.getMessagesResource().list(new MessagesParameters(new Pagination(page, 1), null), new CallbackWebApi<MessagesListResponse>() {
                @Override
                public void success(MessagesListResponse messagesListResponse) {
                    mResponse = messagesListResponse;
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

            Message message = mResponse.getMessages().get(0);

            switch (page) {
                case 0:
                    assertEquals(1, mResponse.getMessages().size());
                    assertEquals(Long.valueOf(0), mResponse.getPageNumber());
                    assertEquals(Long.valueOf(5), mResponse.getPageCount());
                    assertEquals(Long.valueOf(1), mResponse.getNextPage());
                    assertEquals(Long.valueOf(1), mResponse.getPageSize());

                    List<Attachment> attachments = message.getAttachments();
                    assertEquals("134625", message.getId());
                    assertEquals("WCM", message.getFrom());
                    assertEquals("test attach", message.getSubject());
                    assertEquals(TimeUtils.getISO8601Date("2016-04-08T09:20:32+02:00"), message.getDate());
                    assertEquals(1, attachments.size());
                    Attachment attachment = attachments.get(0);
                    assertEquals("jellyfishgrg.jpg", attachment.getId());
                    assertEquals("jellyfishgrg.jpg", attachment.getFileName());
                    break;

                case 1:
                    assertEquals(1, mResponse.getMessages().size());
                    assertEquals(Long.valueOf(1), mResponse.getPageNumber());
                    assertEquals(Long.valueOf(5), mResponse.getPageCount());
                    assertEquals(Long.valueOf(2), mResponse.getNextPage());
                    assertEquals(Long.valueOf(1), mResponse.getPageSize());

                    assertEquals("278583", message.getId());
                    assertEquals("WCM", message.getFrom());
                    assertEquals("Pozor - evidence dluhu na Vašem úvěrovém případně osobním účtu! Hrozí naúčtování poplatků", message.getSubject());
                    assertEquals(TimeUtils.getISO8601Date("2016-04-27T08:20:32+02:00"), message.getDate());
                    assertEquals("<html>\n <head></head>\n <body>\n  <p>Vážená paní/Vážený pane,</p>\n  <p>dovolujeme si Váš upozornit, že u Vás evidujeme dlužnou částku po splatnosti nebo nepovolený záporný zůstatek na osobním účtu. Uhraďte, prosím, tuto dlužnou částku co nejdříve. Zastavíte si tak navyšování úroku z prodlení.</p>\n  <p>Pokud byla dlužná částka již uhrazena, děkujeme Vám a této naší zprávy&nbsp;si nevšímejte.</p>\n  <p>.....................</p>\n  <p>Česká spořitelna</p> \n </body>\n</html>", message.getBody());
                    assertEquals(Arrays.asList("mandatory"), message.getFlags());
                    break;
            }

        }

    }

}

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class MessagesMandatoryListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "messages.mandatory.list";
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
    public void testMandatoryMessagesList() {
        mNetbankingClient.getMessagesResource().getMandatoryResource().list(new CallbackWebApi<MessagesListResponse>() {
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

        assertEquals("278583", message.getId());
        assertEquals("WCM", message.getFrom());
        assertEquals("Pozor - evidence dluhu na Vašem úvěrovém případně osobním účtu! Hrozí naúčtování poplatků", message.getSubject());
        assertEquals("<html>\n <head></head>\n <body>\n  <p>Vážená paní/Vážený pane,</p>\n  <p>dovolujeme si Váš upozornit, že u Vás evidujeme dlužnou částku po splatnosti nebo nepovolený záporný zůstatek na osobním účtu. Uhraďte, prosím, tuto dlužnou částku co nejdříve. Zastavíte si tak navyšování úroku z prodlení.</p>\n  <p>Pokud byla dlužná částka již uhrazena, děkujeme Vám a této naší zprávy&nbsp;si nevšímejte.</p>\n  <p>.....................</p>\n  <p>Česká spořitelna</p> \n </body>\n</html>", message.getBody());
        assertEquals(Arrays.asList("mandatory"), message.getFlags());
    }
}
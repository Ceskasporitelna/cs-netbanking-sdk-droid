package cz.csas.netbanking.cards;

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
import cz.csas.netbanking.Format;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.StatementsDownloadParameters;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardAccountsStatementsDownloadTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "cards.withId.accounts.withId.statements.download";
    private CountDownLatch mCardsSignal;
    private WebApiStream mWebApiStream;

    @Override
    public void setUp() {
        super.setUp();
        mCardsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testCardAccountsStatementsList() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";

        StatementsDownloadParameters parameters = new StatementsDownloadParameters(Format.PDF_A4,
                "06029392819b0198");
        mNetbankingClient.getCardsResource().withId(cardId).getAccountsResource().withId(accountId)
                .getStatements().download(parameters, new CallbackWebApi<WebApiStream>() {
            @Override
            public void success(WebApiStream webApiStream) {
                mWebApiStream = webApiStream;
                mCardsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCardsSignal.countDown();
            }
        });

        try {
            mCardsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Long size = (long) mWebApiStream.getInputStream().available();
            assertEquals("test-pdf.pdf", mWebApiStream.getFilename());
            assertEquals(mWebApiStream.getContentLength(), size);
            assertEquals("application/pdf", mWebApiStream.getContentType().toLowerCase());
            assertPdf(mWebApiStream.getInputStream());
        } catch (IOException e) {
            fail();
        }
    }

}

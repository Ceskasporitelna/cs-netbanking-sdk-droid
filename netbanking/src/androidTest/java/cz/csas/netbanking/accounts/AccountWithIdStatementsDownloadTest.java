package cz.csas.netbanking.accounts;

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
 * The type Account with id statements download test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.04.16.
 */
public class AccountWithIdStatementsDownloadTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "accounts.withId.statements.download";
    private CountDownLatch mStatementsSignal;
    private WebApiStream mWebApiStream;

    @Override
    public void setUp() {
        super.setUp();
        mStatementsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account with id statements download.
     */
    @Test
    public void testAccountWithIdStatementsDownload() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        StatementsDownloadParameters parameters =
                new StatementsDownloadParameters(Format.PDF_A4, "06029392819b0198");

        mNetbankingClient.getAccountsResource().withId(accountId).getStatementsResource().download(parameters, new CallbackWebApi<WebApiStream>() {
            @Override
            public void success(WebApiStream webApiStream) {
                mWebApiStream = webApiStream;
                mStatementsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mStatementsSignal.countDown();
            }
        });

        try {
            mStatementsSignal.await(20, TimeUnit.SECONDS); // wait for callback
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

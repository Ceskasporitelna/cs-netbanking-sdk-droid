package cz.csas.netbanking.cards;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.WebApiStream;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.TransactionField;
import cz.csas.netbanking.ExportTransactionsParameters;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 19.04.16.
 */
public class CardWithIdTransactionsExportTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.transactions.export";
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
    public void testCardTransactionsExport() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+2"));

        ExportTransactionsParameters parameters = new ExportTransactionsParameters.Builder()
                .setFields(Arrays.asList(TransactionField.BOOKING_DATE, TransactionField.PARTNER,
                        TransactionField.AMOUNT, TransactionField.CURRENCY))
                .setShowAccountName(true)
                .setShowAccountNumber(true)
                .setShowTimespan(true)
                .setShowBalance(true)
                .setDateFrom(TimeUtils.getISO8601Date("1999-09-27T00:00:00+02:00"))
                .setDateTo(TimeUtils.getISO8601Date("2000-09-27T00:00:00+02:00"))
                .build();

        mNetbankingClient.getCardsResource().withId(cardId).getTransactionsResource().getExportResource()
                .export(parameters, new CallbackWebApi<WebApiStream>() {
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

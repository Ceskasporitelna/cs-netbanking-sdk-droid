package cz.csas.netbanking.securities;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.Transaction;
import cz.csas.netbanking.TransactionUpdateRequest;
import cz.csas.netbanking.TransactionUpdateResponse;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class SecuritiesTransactionsUpdateTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "securities.withId.transactions.withId.update";
    private CountDownLatch mCountDownLatch;
    private TransactionUpdateResponse mResponse;

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
    public void testSecuritiesTransationsUpdate() {
        String securityId = "420A817C20E4814C7C516A53ABA8E78F0CDBE324";
        String transactionId = "100000189114334";
        TransactionUpdateRequest request = new TransactionUpdateRequest(
                "100000189114334", "New client's personal note for transaction", Arrays.asList("hasStar"));
        mNetbankingClient.getSecuritiesResource().withId(securityId).getTransactionsResource()
                .withId(transactionId).update(request, new CallbackWebApi<TransactionUpdateResponse>() {
            @Override
            public void success(TransactionUpdateResponse transactionUpdateResponse) {
                mResponse = transactionUpdateResponse;
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

        assertEquals(SigningState.NONE, mResponse.signing().getSigningState());
        Transaction transaction = mResponse.getTransaction();

        assertEquals("100000189114334", transaction.getId());
        assertEquals("New client's personal note for transaction", transaction.getNote());
        assertEquals(Arrays.asList("hasNote", "hasStar"), transaction.getFlags());
    }
}

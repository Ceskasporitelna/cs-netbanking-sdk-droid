package cz.csas.netbanking.contracts.pensions;

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
import cz.csas.netbanking.TransactionUpdateRequest;
import cz.csas.netbanking.TransactionUpdateResponse;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class PensionTransactionUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.pensions.withId.transactions.withId.update";
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
    public void testPensionWithIdTransactionUpdate() {
        String pensionId = "E7DD68AA3FF4487AF75626F901761B071E72FFFC";
        String transactionId = "ADAD9879ADKJH9713";
        TransactionUpdateRequest request = new TransactionUpdateRequest("ADAD9879ADKJH9713", "New client's personal note for transaction", Arrays.asList("hasStar"));
        mNetbankingClient.getContractsResource().getPensionsResource().withId(pensionId)
                .getTransactionsResource().withId(transactionId).update(request, new CallbackWebApi<TransactionUpdateResponse>() {
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

        TransactionUpdateResponse transaction = mResponse;
        assertEquals(transactionId, transaction.getTransaction().getId());
        assertEquals("New client's personal note for transaction", transaction.getTransaction().getNote());
        assertEquals(Arrays.asList("hasNote", "hasStar"), transaction.getTransaction().getFlags());
        assertEquals(SigningState.NONE, transaction.signing().getSigningState());
    }
}

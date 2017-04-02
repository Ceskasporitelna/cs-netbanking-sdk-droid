package cz.csas.netbanking.cards;

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

import static org.junit.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardWithIdTransactionUpdateTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.transactions.withId.update";
    private CountDownLatch mCardsSignal;
    private TransactionUpdateResponse mTransaction;

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
    public void testCardWithIdTransationUpdate() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        String transactionId = "23498";

        TransactionUpdateRequest request = new TransactionUpdateRequest("note",
                Arrays.asList("hasStar"));

        mNetbankingClient.getCardsResource().withId(cardId).getTransactionsResource().withId(transactionId)
                .update(request, new CallbackWebApi<TransactionUpdateResponse>() {
                    @Override
                    public void success(TransactionUpdateResponse cardTransaction) {
                        mTransaction = cardTransaction;
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

        assertEquals("23498", mTransaction.getTransaction().getId());
        assertEquals("note", mTransaction.getTransaction().getNote());
        assertEquals(Arrays.asList("hasNote", "hasStar"), mTransaction.getTransaction().getFlags());
        assertEquals(SigningState.NONE, mTransaction.signing().getSigningState());
    }

}

package cz.csas.netbanking.accounts;

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
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.Transaction;
import cz.csas.netbanking.TransactionUpdateRequest;
import cz.csas.netbanking.TransactionUpdateResponse;

import static junit.framework.Assert.assertEquals;

/**
 * The type Account with id transaction update test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountWithIdTransactionUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.transactions.withId.update";
    private CountDownLatch mTransactionsSignal;
    private TransactionUpdateResponse mTransactionUpdateResponse;

    @Override
    public void setUp() {
        super.setUp();
        mTransactionsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account with id transaction with id update.
     */
    @Test
    public void testAccountWithIdTransactionWithIdUpdate() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        String transactionId = "39876";

        TransactionUpdateRequest request = new TransactionUpdateRequest("note", Arrays.asList("hasStar"));
        mNetbankingClient.getAccountsResource().withId(accountId).getTransactionsResource().withId(transactionId).update(request, new CallbackWebApi<TransactionUpdateResponse>() {
            @Override
            public void success(TransactionUpdateResponse accountTransactionUpdateResponse) {
                mTransactionUpdateResponse = accountTransactionUpdateResponse;
                mTransactionsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mTransactionsSignal.countDown();
            }
        });

        try {
            mTransactionsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Transaction transaction = mTransactionUpdateResponse.getTransaction();
        SigningObject signingObject = mTransactionUpdateResponse.signing();

        assertEquals("39876", transaction.getId());
        assertEquals("note", transaction.getNote());
        assertEquals(Arrays.asList("hasNote", "hasStar"), transaction.getFlags());
        assertEquals(SigningState.NONE, signingObject.getSigningState());
    }
}

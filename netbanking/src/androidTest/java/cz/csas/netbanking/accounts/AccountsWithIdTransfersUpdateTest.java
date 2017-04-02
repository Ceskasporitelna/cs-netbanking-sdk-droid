package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.TransferResponse;

import static junit.framework.Assert.assertEquals;

/**
 * The type Accounts with id transfers update test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.04.16.
 */
public class AccountsWithIdTransfersUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.transfers.update";
    private CountDownLatch mTransferSignal;
    private TransferResponse mTransferResponse;

    @Override
    public void setUp() {
        super.setUp();
        mTransferSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account transfer update.
     */
    @Test
    public void testAccountTransferUpdate() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";

        AccountTransferRequest request = new AccountTransferRequest(
                AccountsTransferType.REVOLVING_LOAN_DISBURSEMENT,
                TimeUtils.getPlainDate("2015-02-28"),
                "moje prve cerpanie z penize na klik",
                new Amount(1000L, 2, "CZK"));

        mNetbankingClient.getAccountsResource().withId(accountId).getTransferResource().update(request, new CallbackWebApi<TransferResponse>() {
            @Override
            public void success(TransferResponse transferResponse) {
                mTransferResponse = transferResponse;
                mTransferSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mTransferSignal.countDown();
            }
        });

        try {
            mTransferSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(SigningState.OPEN, mTransferResponse.signing().getSigningState());
        assertEquals("151112531008724", mTransferResponse.signing().getSignId());
    }


}

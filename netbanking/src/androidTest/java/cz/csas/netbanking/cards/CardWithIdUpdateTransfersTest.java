package cz.csas.netbanking.cards;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.TransferResponse;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 20.04.16.
 */
public class CardWithIdUpdateTransfersTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "cards.withId.transfers.update";
    private CountDownLatch mCardsSignal;
    private TransferResponse mTransferResponse;

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
    public void testCardWithIdUpdateTransfers() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";

        CardTransferRequest request = new CardTransferRequest(CardTransferType.DEBT_REPAYMENT,
                new Sender(null, new AccountNumber("2326573123", "0800")),
                new Amount(500000L, 2, "CZK"));

        mNetbankingClient.getCardsResource().withId(cardId).getTransferResource().update(request, new CallbackWebApi<TransferResponse>() {
            @Override
            public void success(TransferResponse transferResponse) {
                mTransferResponse = transferResponse;
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

        assertEquals(SigningState.OPEN, mTransferResponse.signing().getSigningState());
        assertEquals("151112531008554", mTransferResponse.signing().getSignId());
    }
}

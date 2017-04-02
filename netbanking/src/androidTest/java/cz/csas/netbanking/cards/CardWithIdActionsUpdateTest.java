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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 19.04.16.
 */
public class CardWithIdActionsUpdateTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.actions.update";
    private CountDownLatch mCardsSignal;
    private CardActionResponse mResponse;

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

        CardActionRequest request = new CardActionRequest(CardAction.ACTIVATE_CARD);

        mNetbankingClient.getCardsResource().withId(cardId).getActionsResource().update(request, new CallbackWebApi<CardActionResponse>() {
            @Override
            public void success(CardActionResponse cardActionResponse) {
                mResponse = cardActionResponse;
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

        assertEquals(SigningState.OPEN, mResponse.signing().getSigningState());
        assertEquals("1883293134", mResponse.signing().getSignId());
    }

}

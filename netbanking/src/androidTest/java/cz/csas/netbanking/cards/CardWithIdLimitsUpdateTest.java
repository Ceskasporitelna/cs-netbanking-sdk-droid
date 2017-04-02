package cz.csas.netbanking.cards;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 19.04.16.
 */
public class CardWithIdLimitsUpdateTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.limits.update";
    private CountDownLatch mCardsSignal;
    private ChangeCardLimitsResponse mResponse;

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
    public void testCardsWithIdLimitsUpdate() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";

        CardLimit newLimit = new CardLimit(CardLimitType.ATM, CardLimitPeriod.FIVE_D,
            new Amount(1100000L, 2, "CZK"), null, null, null);
        ChangeCardLimitsRequest request = new ChangeCardLimitsRequest(Arrays.asList(newLimit), null);

        mNetbankingClient.getCardsResource().withId(cardId).getLimitsResource().update(request, new CallbackWebApi<ChangeCardLimitsResponse>() {
            @Override
            public void success(ChangeCardLimitsResponse cardLimitsListResponse) {
                mResponse = cardLimitsListResponse;
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
        assertEquals("1480132234", mResponse.signing().getSignId());

        List<CardLimit> limits = mResponse.getLimits();
        assertEquals(3, limits.size());

        for(int i=0; i<limits.size(); ++i) {
            CardLimit limit = limits.get(i);
            Amount limitAmount = limit.getLimit();
            Amount bankLimitAmount = limit.getBankLimit();

            switch(i) {
                case 0:
                    assertEquals(CardLimitType.ATM, limit.getLimitType());
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(1100000), limitAmount.getValue());
                    assertEquals(Integer.valueOf(2), limitAmount.getPrecision());
                    assertEquals("CZK", limitAmount.getCurrency());
                    assertEquals(Long.valueOf(7000000), bankLimitAmount.getValue());
                    assertEquals(Integer.valueOf(2), bankLimitAmount.getPrecision());
                    assertEquals("CZK", bankLimitAmount.getCurrency());
                    break;
                case 1:
                    assertEquals(CardLimitType.POS, limit.getLimitType());
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(5000000), limitAmount.getValue());
                    assertEquals(Integer.valueOf(2), limitAmount.getPrecision());
                    assertEquals("CZK", limitAmount.getCurrency());
                    assertEquals(Long.valueOf(50000000), bankLimitAmount.getValue());
                    assertEquals(Integer.valueOf(2), bankLimitAmount.getPrecision());
                    assertEquals("CZK", bankLimitAmount.getCurrency());
                    break;
                case 2:
                    assertEquals(CardLimitType.INTERNET, limit.getLimitType());
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(500000), limitAmount.getValue());
                    assertEquals(Integer.valueOf(2), limitAmount.getPrecision());
                    assertEquals("CZK", limitAmount.getCurrency());
                    assertEquals(Long.valueOf(50000000), bankLimitAmount.getValue());
                    assertEquals(Integer.valueOf(2), bankLimitAmount.getPrecision());
                    assertEquals("CZK", bankLimitAmount.getCurrency());
                    break;
            }
        }
    }

}

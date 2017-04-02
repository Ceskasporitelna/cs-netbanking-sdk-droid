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
import cz.csas.netbanking.Address;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardWithIdDeliveryTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.delivery.get";
    private CountDownLatch mCardsSignal;
    private CardDelivery mCardDelivery;

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
    public void testCardWithIdDelivery() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        mNetbankingClient.getCardsResource().withId(cardId).getDeliveryResource().get(new CallbackWebApi<CardDelivery>() {
            @Override
            public void success(CardDelivery cardDelivery) {
                mCardDelivery = cardDelivery;
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

        assertEquals(CardDeliveryMode.BRANCH, mCardDelivery.getCardDeliveryMode());
        assertEquals("1075", mCardDelivery.getBranchId());

        Address address = mCardDelivery.getAddress();
        assertEquals("Antala Staška", address.getStreet());
        assertEquals("1292", address.getBuildingApartment());
        assertEquals("32", address.getStreetNumber());
        assertEquals("Praha", address.getCity());
        assertEquals("14000", address.getZipCode());
        assertEquals("CZ", address.getCountry());

    }

}

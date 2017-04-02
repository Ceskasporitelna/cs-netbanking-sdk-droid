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
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.Address;
import cz.csas.netbanking.Language;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardWithIdDeliveryUpdateTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "cards.withId.delivery.update";
    private CountDownLatch mCardsSignal;
    private ChangeCardDeliverySettingsResponse mResponse;

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
    public void testCardWithIdDeliveryUpdate() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";

        ChangeCardDeliverySettingsRequest request = new ChangeCardDeliverySettingsRequest(CardDeliveryMode.BRANCH,
                Arrays.asList(new Confirmation("john.doe@test.com", Language.CS)));

        mNetbankingClient.getCardsResource().withId(cardId).getDeliveryResource().update(request,
                new CallbackWebApi<ChangeCardDeliverySettingsResponse>() {
            @Override
            public void success(ChangeCardDeliverySettingsResponse response) {
                mResponse = response;
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

        assertEquals(CardDeliveryMode.BRANCH, mResponse.getCardDeliveryMode());
        assertEquals("1075", mResponse.getBranchId());

        Address address = mResponse.getAddress();
        assertEquals("Antala Staška", address.getStreet());
        assertEquals("1292", address.getBuildingApartment());
        assertEquals("32", address.getStreetNumber());
        assertEquals("Praha", address.getCity());
        assertEquals("14000", address.getZipCode());
        assertEquals("CZ", address.getCountry());

        List<Confirmation> confirmations = mResponse.getConfirmations();
        Confirmation confirmation = confirmations.get(0);
        assertEquals("john.doe@test.com", confirmation.getEmail());
        assertEquals(Language.CS, confirmation.getLanguage());

        SigningObject signingObject = mResponse.signing();
        assertEquals(SigningState.OPEN, signingObject.getSigningState());
        assertEquals("151112531008554", signingObject.getSignId());
    }

}

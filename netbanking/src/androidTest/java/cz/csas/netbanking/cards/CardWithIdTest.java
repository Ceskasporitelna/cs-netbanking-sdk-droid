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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardWithIdTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "cards.withId.get";
    private CountDownLatch mCardsSignal;
    private Card mCard;

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
    public void testCardWithId() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        mNetbankingClient.getCardsResource().withId(cardId).get(new CallbackWebApi<Card>() {
            @Override
            public void success(Card card) {
                mCard = card;
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

        Card card = mCard;
        CardMainAccount account = card.getMainAccount();
        AccountNumber accountNumber = account.getAccountno();

        assertEquals("33A813886442D946122C78305EC4E482DE9F574D", card.getId());
        assertEquals("VOJTÍŠKOVÁ ANNA", card.getOwner());
        assertEquals("451161XXXXXX1987", card.getNumber());
        assertEquals("2", card.getSequenceNumber());
        assertEquals("4511611", card.getProductCode());
        assertEquals(TimeUtils.getPlainDate("2018-03-31"), card.getExpiryDate());
        assertEquals(CardState.TEMPORARY_BLOCKED, card.getState());
        assertEquals(CardType.BANK_CARD, card.getType());
        assertEquals(CardProvider.ERSTE_BANK, card.getProvider());
        assertEquals(TimeUtils.getPlainDate("2015-04-01"), card.getValidFromDate());
        assertEquals(CardCharacteristic.MAIN, card.getCharacteristic());

        assertEquals("076E1DBCCCD38729A99D93AC8D3E8273237C7E36", account.getId());
        assertEquals("Anna Vojtíšková", account.getHolderName());
        assertEquals("2328489013", accountNumber.getNumber());
        assertEquals("0800", accountNumber.getBankCode());
        assertEquals("CZ", accountNumber.getCountryCode());
        assertEquals("CZ5908000000002328489013", accountNumber.getCzIban());
        assertEquals("GIBACZPX", accountNumber.getCzBic());

        assertEquals(CardDeliveryMode.HOME, card.getCardDeliveryMode());
        assertEquals(Arrays.asList(
                "automaticReplacementOn",
                "secureOnlineShoppingEnabled",
                "activationAllowed",
                "contactlessEnabled"), card.getFlags());
        assertEquals(Arrays.asList(
                "contactless",
                "replacementCard",
                "secureOnlineShopping"), card.getFeatures());
        assertEquals(LockReason.LOSS, card.getLockReason());
        assertEquals("Visa Classic debetní - Partner", card.getProductI18N());

    }

}

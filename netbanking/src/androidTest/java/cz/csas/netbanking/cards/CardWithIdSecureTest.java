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
import cz.csas.netbanking.Language;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.04.16.
 */
public class CardWithIdSecureTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "cards.withId.secure3D.get";
    private CountDownLatch mCardsSignal;
    private SecureSettings mSecureSettings;

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
    public void testCardWithIdSecure() {
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
        mNetbankingClient.getCardsResource().withId(cardId).getSecure3DResource().get(new CallbackWebApi<SecureSettings>() {
            @Override
            public void success(SecureSettings secureSettings) {
                mSecureSettings = secureSettings;
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

        assertEquals(SecureSettingsStatus.OK, mSecureSettings.getStatus());
        assertEquals("+420739473460", mSecureSettings.getPhoneNumber());
        assertEquals(Language.CS, mSecureSettings.getLanguage());

    }

}

package cz.csas.netbanking.settings;

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
import cz.csas.netbanking.Language;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class SettingsUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "settings.update";
    private CountDownLatch mCountDownLatch;
    private SettingsUpdateResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testSettingsUpdate() {
        SettingsUpdateRequest request = new SettingsUpdateRequest(Language.CS,
                Arrays.asList("displayInsurances", "displayBuildings"));

        mNetbankingClient.getSettingsResource().update(request, new CallbackWebApi<SettingsUpdateResponse>() {
            @Override
            public void success(SettingsUpdateResponse settingsUpdateResponse) {
                mResponse = settingsUpdateResponse;
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Language.CS, mResponse.getSettings().getLanguage());
        assertEquals(Arrays.asList("displayInsurances", "displayBuildings", "displayCreditCards",
                "displayInvestments"), mResponse.getSettings().getFlags());

        assertEquals(SigningState.NONE, mResponse.signing().getSigningState());
    }
}

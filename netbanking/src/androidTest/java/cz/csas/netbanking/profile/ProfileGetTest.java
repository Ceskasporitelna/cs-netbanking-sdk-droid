package cz.csas.netbanking.profile;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * Profile get test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class ProfileGetTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "profile.get";
    private CountDownLatch mProfileSignal;
    private Profile mProfile;

    @Override
    public void setUp() {
        super.setUp();
        mProfileSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test profile.
     */
    @Test
    public void testProfile() {
        mNetbankingClient.getProfileResource().get(new CallbackWebApi<Profile>() {
            @Override
            public void success(Profile profile) {
                mProfile = profile;
                mProfileSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mProfileSignal.countDown();
            }
        });

        try {
            mProfileSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals("Anna", mProfile.getFirstName());
        assertEquals("Vojtíšková", mProfile.getLastName());
        assertEquals("2002-12-02-00.17.40.959689", mProfile.getCustomerId());
        assertEquals(Integer.valueOf(1), mProfile.getInstituteId());
        assertEquals(MarketingInfoAcceptance.UNKNOWN, mProfile.getMarketingInfoAcceptance());
        assertEquals(Gender.FEMALE, mProfile.getGender());
        assertEquals(TimeUtils.getISO8601Date("2016-03-17T15:01:49+01:00"), mProfile.getLastLogin());
        assertEquals("Anno Vojtíšková", mProfile.getSalutation());
    }
}

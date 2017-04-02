package cz.csas.netbanking.profile;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
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
 * Profile last login test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class ProfileLastLoginTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "profile.lastLogin.list";
    private CountDownLatch mProfileSignal;
    private LastLoginListResponse mResponse;

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
     * Test last login info list.
     */
    @Test
    public void testLastLoginList() {
        mNetbankingClient.getProfileResource().getLastLogins().list(new CallbackWebApi<LastLoginListResponse>() {
            @Override
            public void success(LastLoginListResponse lastLoginListResponse) {
                mResponse = lastLoginListResponse;
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

        List<LastLoginInfo> infoList = mResponse.getItems();
        assertEquals(1, infoList.size());
        assertEquals(TimeUtils.getISO8601Date("2016-03-17T15:01:49+01:00"), infoList.get(0).getLastLogin());
        assertEquals("GEORGE", infoList.get(0).getChannel());
    }


}

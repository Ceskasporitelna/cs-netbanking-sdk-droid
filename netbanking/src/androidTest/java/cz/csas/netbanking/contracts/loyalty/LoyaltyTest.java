package cz.csas.netbanking.contracts.loyalty;

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
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class LoyaltyTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "contracts.loyalty.get";
    private CountDownLatch mCountDownLatch;
    private Loyalty mResponse;

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
    public void testLoyalty() {
        mNetbankingClient.getContractsResource().getLoyaltyResource().get(new CallbackWebApi<Loyalty>() {
            @Override
            public void success(Loyalty loyalty) {
                mResponse = loyalty;
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

        assertEquals(TimeUtils.getISO8601Date("2016-05-31T00:00:00+02:00"), mResponse.getExportDate());
        assertEquals(LoyaltyState.UNREGISTERED, mResponse.getState());
        assertEquals(Double.valueOf(0), mResponse.getPointsCount());
        assertEquals("15B8FE1760", mResponse.getActivationCode());
    }
}

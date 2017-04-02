package cz.csas.netbanking.goals;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class GoalsUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "goals.update";
    private CountDownLatch mCountDownLatch;
    private GoalsListResponse mResponse;

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
    public void testGoalsUpdate() {
        GoalsUpdateRequest request = new GoalsUpdateRequest(Arrays.asList(
                new Goal("Auto", new Amount(4000l, 2, "CZK"), new Date(1445554800000l), false)
        ));
        mNetbankingClient.getGoalsResource().update(request, new CallbackWebApi<GoalsListResponse>() {
            @Override
            public void success(GoalsListResponse goalsListResponse) {
                mResponse = goalsListResponse;
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

        List<Goal> goals = mResponse.getGoals();
        assertEquals(1, goals.size());
        Goal goal = goals.get(0);

        assertEquals("Auto", goal.getName());
        assertEquals(Long.valueOf(4000), goal.getPrice().getValue());
        assertEquals(Integer.valueOf(2), goal.getPrice().getPrecision());
        assertEquals("CZK", goal.getPrice().getCurrency());
        assertEquals(new Date(1445554800000l), goal.getDeadline());
        assertEquals(Boolean.valueOf(false), goal.getCompleted());
    }
}

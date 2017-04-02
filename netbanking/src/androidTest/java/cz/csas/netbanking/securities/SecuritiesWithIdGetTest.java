package cz.csas.netbanking.securities;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class SecuritiesWithIdGetTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "securities.withId.get";
    private CountDownLatch mCountDownLatch;
    private Security mResponse;

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
    public void testSecuritiesWithId() {
        String id = "420A817C20E4814C7C516A53ABA8E78F0CDBE324";
        mNetbankingClient.getSecuritiesResource().withId(id).get(new CallbackWebApi<Security>() {
            @Override
            public void success(Security security) {
                mResponse = security;
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

        Security account = mResponse;
        assertEquals("420A817C20E4814C7C516A53ABA8E78F0CDBE324", account.getId());
        assertEquals(Long.valueOf(1345437), account.getBalance().getValue());
        assertEquals(Integer.valueOf(2), account.getBalance().getPrecision());
        assertEquals("CZK", account.getBalance().getCurrency());
        assertEquals("Aleš Vrba", account.getDescription());
        assertEquals("1034176627", account.getAccountno());
    }
}

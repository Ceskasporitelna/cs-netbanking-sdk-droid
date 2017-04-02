package cz.csas.netbanking.authorizationLimits;

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
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class AuthorizationLimitsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "authorizationLimits.list";
    private CountDownLatch mCountDownLatch;
    private AuthorizationLimitsListResponse mResponse;

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
    public void testAuthorizationLimitsList() {
        AuthorizationLimitsParameters request = new AuthorizationLimitsParameters(ApplicationIdParameter.GEORGE);
        mNetbankingClient.getAuthorizationLimitsResource().list(request, new CallbackWebApi<AuthorizationLimitsListResponse>() {
            @Override
            public void success(AuthorizationLimitsListResponse authorizationLimitsListResponse) {
                mResponse = authorizationLimitsListResponse;
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

        List<AuthorizationLimit> limits = mResponse.getItems();
        assertEquals(2, limits.size());

        for (int i = 0; i < limits.size(); i++) {
            AuthorizationLimit limit = limits.get(i);
            Amount dailyLimit = limit.getDailyLimit();
            Amount transactionLimit = limit.getTransactionLimit();
            Amount maxBankLimit = limit.getMaxBankLimit();
            switch (i) {
                case 0:
                    assertEquals("934872973982", limit.getId());
                    assertEquals(AuthorizationType.TAC, limit.getAuthorizationType());
                    assertEquals(ChannelId.NET_BANKING, limit.getChannelId());
                    assertEquals(ApplicationId.GEORGE, limit.getApplicationId());
                    assertEquals(Long.valueOf(400000), dailyLimit.getValue());
                    assertEquals(Integer.valueOf(2), dailyLimit.getPrecision());
                    assertEquals("EUR", transactionLimit.getCurrency());
                    assertEquals(Long.valueOf(100000), transactionLimit.getValue());
                    assertEquals(Integer.valueOf(2), transactionLimit.getPrecision());
                    assertEquals("EUR", dailyLimit.getCurrency());
                    assertEquals(Long.valueOf(1700000), maxBankLimit.getValue());
                    assertEquals(Integer.valueOf(2), maxBankLimit.getPrecision());
                    assertEquals("EUR", maxBankLimit.getCurrency());
                    break;
                case 1:
                    assertEquals("934872973987", limit.getId());
                    assertEquals(AuthorizationType.TAC, limit.getAuthorizationType());
                    assertEquals(ChannelId.NET_BANKING, limit.getChannelId());
                    assertEquals(ApplicationId.INTERNET_BANKING, limit.getApplicationId());
                    assertEquals(Long.valueOf(150000), dailyLimit.getValue());
                    assertEquals(Integer.valueOf(2), dailyLimit.getPrecision());
                    assertEquals("EUR", transactionLimit.getCurrency());
                    assertEquals(Long.valueOf(90000), transactionLimit.getValue());
                    assertEquals(Integer.valueOf(2), transactionLimit.getPrecision());
                    assertEquals("EUR", dailyLimit.getCurrency());
                    assertEquals(Long.valueOf(900000), maxBankLimit.getValue());
                    assertEquals(Integer.valueOf(2), maxBankLimit.getPrecision());
                    assertEquals("EUR", maxBankLimit.getCurrency());
                    break;
            }
        }
    }
}

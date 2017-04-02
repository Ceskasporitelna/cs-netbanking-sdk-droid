package cz.csas.netbanking.authorizationToken;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.client.rest.CsRestError;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.accounts.AccountsListResponse;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 06.09.16.
 */
public class AuthorizationTokenInvalidTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "authorizationToken.invalid";
    private CountDownLatch mCountDownLatch;
    private CsRestError mError;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        headers.put("authorization", "bearer cn389ncoiwuencr");
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testAuthorizationTokenInvalid() {
        mNetbankingClient.getAccountsResource().list(null, new CallbackWebApi<AccountsListResponse>() {
            @Override
            public void success(AccountsListResponse accountsListResponse) {
                fail();
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mError = (CsRestError) error;
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(403, mError.getResponse().getStatus());
    }
}

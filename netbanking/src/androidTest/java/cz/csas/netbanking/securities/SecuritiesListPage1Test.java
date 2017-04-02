package cz.csas.netbanking.securities;

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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class SecuritiesListPage1Test extends NetbankingTest {

    private final String X_JUDGE_CASE = "securities.list.page1";
    private CountDownLatch mCountDownLatch;
    private SecuritiesListResponse mResponse;

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
    public void testSecuritiesPage1List() {
        mNetbankingClient.getSecuritiesResource().list(new PaginatedParameters(new Pagination(1, 1)), new CallbackWebApi<SecuritiesListResponse>() {
            @Override
            public void success(SecuritiesListResponse securitiesListResponse) {
                mResponse = securitiesListResponse;
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

        assertEquals(Long.valueOf(1), mResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());

        List<Security> accounts = mResponse.getSecuritiesAccounts();
        assertEquals(1, accounts.size());

        Security account = accounts.get(0);
        assertEquals("420A217C20E4814C7C516A53ABA8E78F8CDBE324", account.getId());
        assertEquals(Long.valueOf(1345412), account.getBalance().getValue());
        assertEquals(Integer.valueOf(2), account.getBalance().getPrecision());
        assertEquals("CZK", account.getBalance().getCurrency());
        assertEquals("Aleš Vrba", account.getDescription());
        assertEquals("1034176627", account.getAccountno());

    }
}

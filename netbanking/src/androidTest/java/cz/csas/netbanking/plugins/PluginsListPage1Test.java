package cz.csas.netbanking.plugins;

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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class PluginsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "plugins.list.page1";
    private CountDownLatch mCountDownLatch;
    private PluginsListResponse mResponse;

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
    public void testPluginsListPage1() {
        mNetbankingClient.getPluginsResource().list(new PaginatedParameters(new Pagination(1, 1)),
                new CallbackWebApi<PluginsListResponse>() {
                    @Override
                    public void success(PluginsListResponse pluginsListResponse) {
                        mResponse = pluginsListResponse;
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

        List<Plugin> plugins = mResponse.getPlugins();
        assertEquals(1, plugins.size());
        Plugin plugin = plugins.get(0);

        assertEquals("PFM_1", plugin.getProductCode());
        assertEquals("PluginBudgets pro CZ", plugin.getName());

        List<StandardFee> standardFees = plugin.getStandardFees();
        assertEquals(1, standardFees.size());
        StandardFee standardFee = standardFees.get(0);

        assertEquals(TimeOfCharging.IMMEDIATELY, standardFee.getTimeOfCharging());
        assertEquals(PeriodOfCharging.NON_RECURRING, standardFee.getPeriodOfCharging());

        Amount amount = standardFee.getAmount();
        assertEquals(Long.valueOf(0), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());

        assertEquals(TimeUtils.getPlainDate("2100-01-01"), plugin.getValidUntil());
    }
}

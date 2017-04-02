package cz.csas.netbanking.contracts.insurances;

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceStrategiesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.strategies.list";
    private CountDownLatch mCountDownLatch;
    private ContractStrategiesListResponse mResponse;

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
    public void testStrategiesList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getStrategiesResource()
                .list(new CallbackWebApi<ContractStrategiesListResponse>() {
            @Override
            public void success(ContractStrategiesListResponse strategiesListResponse) {
                mResponse = strategiesListResponse;
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

        List<ContractStrategy> strategies = mResponse.getStrategies();
        assertEquals(1, strategies.size());

        ContractStrategy strategy = strategies.get(0);
        assertEquals(ContractStrategyType.ACTUAL_SETTING, strategy.getType());
        assertEquals(ContractStrategyGroup.STRATEGY, strategy.getGroup());
        List<StrategyFund> funds = strategy.getFunds();
        StrategyFund fund = funds.get(0);

        assertEquals("5", fund.getCode());
        assertEquals("PČS fond akciový", fund.getName());
        assertEquals(Double.valueOf(0), fund.getShare());
        assertEquals("EDITABLE", fund.getChangeType());
    }
}

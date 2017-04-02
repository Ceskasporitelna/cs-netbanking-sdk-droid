package cz.csas.netbanking.contracts.insurances;

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
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceDeactivateRiskSportsTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.services.deactivateRiskSports";
    private CountDownLatch mCountDownLatch;
    private RiskSportsDeactivationUpdateResponse mResponse;

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
    public void testDeactivateRiskSports() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        RiskSportsUpdateRequest request = new RiskSportsUpdateRequest(
                TimeUtils.getPlainDate("2016-08-16"),
                TimeUtils.getPlainDate("2016-08-20"),
                "602123456"
        );
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getServicesResource()
                .deactivateRiskSports(request, new CallbackWebApi<RiskSportsDeactivationUpdateResponse>() {
            @Override
            public void success(RiskSportsDeactivationUpdateResponse riskSportsActivationUpdateResponse) {
                mResponse = riskSportsActivationUpdateResponse;
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

        assertEquals(SigningState.NONE, mResponse.signing().getSigningState());
    }
}

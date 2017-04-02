package cz.csas.netbanking.contracts.insurances;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancesWithIdFundsUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.funds.update";
    private CountDownLatch mCountDownLatch;
    private FundsUpdateResponse mResponse;

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
    public void testInsurancesWithIdFundsGet() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        FundsUpdateRequest request = new FundsUpdateRequest(
                Arrays.asList(new FundInfo("31", 35d), new FundInfo("32", 65d)),
                InvestmentProgram.INVESTMENT_MANAGEMENT);

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getFundsResource()
                .update(request, new CallbackWebApi<FundsUpdateResponse>() {
            @Override
            public void success(FundsUpdateResponse insuranceFundsUpdateResponse) {
                mResponse = insuranceFundsUpdateResponse;
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

        assertEquals("31", mResponse.getFunds().get(0).getCode());
        assertEquals(Double.valueOf(35), mResponse.getFunds().get(0).getAllocation());
        assertEquals("32", mResponse.getFunds().get(1).getCode());
        assertEquals(Double.valueOf(65), mResponse.getFunds().get(1).getAllocation());
        assertEquals(InvestmentProgram.INVESTMENT_MANAGEMENT, mResponse.getInvestmentProgram());
        assertEquals(SigningState.OPEN, mResponse.signing().getSigningState());
        assertEquals("151112531008724", mResponse.signing().getSignId());
    }
}

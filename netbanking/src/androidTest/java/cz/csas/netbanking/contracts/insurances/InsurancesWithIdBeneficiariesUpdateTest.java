package cz.csas.netbanking.contracts.insurances;

import org.junit.Test;

import java.util.Arrays;
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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancesWithIdBeneficiariesUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.beneficiaries.update";
    private CountDownLatch mCountDownLatch;
    private InsuranceBeneficiariesUpdateResponse mResponse;

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
    public void testInsurancesWithIdBeneficiariesUpdate() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        List<InsuranceBeneficiary> requestBeneficiaries = Arrays.asList(
                new InsuranceBeneficiary(InsuranceBeneficiaryType.PERSON, "Mgr. Rudolf Mrazek", TimeUtils.getPlainDate("1978-01-18"), 20d),
                new InsuranceBeneficiary(InsuranceBeneficiaryType.PERSON, "Bielčik Tomáš", TimeUtils.getPlainDate("2003-09-10"), 40d),
                new InsuranceBeneficiary(InsuranceBeneficiaryType.PERSON, "Bielčiková Eliška", TimeUtils.getPlainDate("2008-06-09"), 40d)
        );
        InsuranceBeneficiariesUpdateRequest request = new InsuranceBeneficiariesUpdateRequest(requestBeneficiaries);

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getBeneficiariesResource().update(request, new CallbackWebApi<InsuranceBeneficiariesUpdateResponse>() {
            @Override
            public void success(InsuranceBeneficiariesUpdateResponse beneficiariesUpdateResponse) {
                mResponse = beneficiariesUpdateResponse;
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

        List<InsuranceBeneficiary> beneficiaries = mResponse.getBeneficiaries();
        for(int i=0; i<beneficiaries.size(); ++i) {
            InsuranceBeneficiary beneficiary = beneficiaries.get(i);
            switch(i) {
                case 0:
                    assertEquals(InsuranceBeneficiaryType.PERSON, beneficiary.getType());
                    assertEquals("Mgr. Rudolf Mrazek", beneficiary.getName());
                    assertEquals(TimeUtils.getPlainDate("1978-01-18"), beneficiary.getBirthdate());
                    assertEquals(Double.valueOf(20), beneficiary.getPercentage());
                    break;
                case 1:
                    assertEquals(InsuranceBeneficiaryType.PERSON, beneficiary.getType());
                    assertEquals("Bielčik Tomáš", beneficiary.getName());
                    assertEquals(TimeUtils.getPlainDate("2003-09-10"), beneficiary.getBirthdate());
                    assertEquals(Double.valueOf(40), beneficiary.getPercentage());
                    break;
                case 2:
                    assertEquals(InsuranceBeneficiaryType.PERSON, beneficiary.getType());
                    assertEquals("Bielčiková Eliška", beneficiary.getName());
                    assertEquals(TimeUtils.getPlainDate("2008-06-09"), beneficiary.getBirthdate());
                    assertEquals(Double.valueOf(40), beneficiary.getPercentage());
                    break;
            }
        }
    }
}

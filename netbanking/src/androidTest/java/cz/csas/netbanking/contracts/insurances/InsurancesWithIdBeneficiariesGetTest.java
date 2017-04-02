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
public class InsurancesWithIdBeneficiariesGetTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.beneficiaries.get";
    private CountDownLatch mCountDownLatch;
    private InsuranceBeneficiariesListResponse mResponse;

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
    public void testInsurancesWithIdBeneficiariesList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getBeneficiariesResource().list(new CallbackWebApi<InsuranceBeneficiariesListResponse>() {
            @Override
            public void success(InsuranceBeneficiariesListResponse beneficiariesListResponse) {
                mResponse = beneficiariesListResponse;
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

        assertEquals(Arrays.asList("beneficiariesChangeAllowed"), mResponse.getFlags());
        List<InsuranceBeneficiary> beneficiaries = mResponse.getBeneficiaries();
        for(int i=0; i<beneficiaries.size(); ++i) {
            InsuranceBeneficiary beneficiary = beneficiaries.get(i);
            switch(i) {
                case 0:
                    assertEquals(InsuranceBeneficiaryType.PERSON, beneficiary.getType());
                    assertEquals("Bielčik Tomáš", beneficiary.getName());
                    assertEquals(TimeUtils.getPlainDate("2003-09-10"), beneficiary.getBirthdate());
                    assertEquals(Double.valueOf(50), beneficiary.getPercentage());
                    break;
                case 1:
                    assertEquals(InsuranceBeneficiaryType.PERSON, beneficiary.getType());
                    assertEquals("Bielčiková Eliška", beneficiary.getName());
                    assertEquals(TimeUtils.getPlainDate("2008-06-09"), beneficiary.getBirthdate());
                    assertEquals(Double.valueOf(50), beneficiary.getPercentage());
                    break;
            }
        }
    }
}

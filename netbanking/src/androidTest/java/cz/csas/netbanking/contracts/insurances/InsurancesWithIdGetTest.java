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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class InsurancesWithIdGetTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.get";
    private CountDownLatch mCountDownLatch;
    private InsuranceDetail mResponse;

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
    public void testInsurancesWithIdGet() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getDetailResource().get(new CallbackWebApi<InsuranceDetail>() {
            @Override
            public void success(InsuranceDetail insurance) {
                mResponse = insurance;
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

        InsuranceDetail insurance = mResponse;
        assertEquals("3961D3F9E922EEE93E2581E896B34566645FE7E3", insurance.getId());
        assertEquals(InsuranceType.LIFE, insurance.getType());
        assertEquals("264", insurance.getProduct());
        assertEquals("Pojištění FLEXI", insurance.getProductI18N());
        assertEquals("Hana Bielčíková", insurance.getInsurancePolicyHolder());
        assertEquals("7009689942", insurance.getPolicyNumber());

        LifeDetail life = insurance.getLife();
        assertEquals(Long.valueOf(60000), life.getPremium().getValue());
        assertEquals(Integer.valueOf(2), life.getPremium().getPrecision());
        assertEquals("CZK", life.getPremium().getCurrency());
        assertEquals(TimeUtils.getPlainDate("2046-12-31"), life.getContractEndDate());
        assertEquals(TimeUtils.getPlainDate("2015-01-01"), life.getContractStartDate());
        assertEquals(Long.valueOf(0), life.getInsuredAmount().getValue());
        assertEquals(Integer.valueOf(2), life.getInsuredAmount().getPrecision());
        assertEquals("CZK", life.getInsuredAmount().getCurrency());
        assertEquals(Interval.MONTHLY, life.getPremiumPaymentInterval());
        assertEquals(TimeUtils.getPlainDate("2015-12-15"), life.getPremiumLastPaid());

        List<PaymentTemplate> paymentTemplates = life.getPaymentTemplates();
        assertEquals(2, paymentTemplates.size());
        for(int i=0; i<paymentTemplates.size(); ++i){
            PaymentTemplate template = paymentTemplates.get(i);
            switch (i) {
                case 0:
                    assertEquals(PaymentTemplateType.ORDINARY, template.getType());
                    assertEquals("7009689942", template.getSymbols().getVariableSymbol());
                    assertEquals("1210230319", template.getReceiver().getNumber());
                    assertEquals("0800", template.getReceiver().getBankCode());
                    break;
                case 1:
                    assertEquals(PaymentTemplateType.EXTRAORDINARY, template.getType());
                    assertEquals("7909689942", template.getSymbols().getVariableSymbol());
                    assertEquals("7909689942", template.getReceiver().getNumber());
                    assertEquals("0800", template.getReceiver().getBankCode());
                    break;
            }
        }

        assertEquals(InsuranceStatus.ACTIVE, insurance.getStatus());
    }
}

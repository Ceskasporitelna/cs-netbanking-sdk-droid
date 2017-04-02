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
 * @since 31.08.16.
 */
public class InsuranceEventsTaxBenefitsTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.taxBenefits.get";
    private CountDownLatch mCountDownLatch;
    private TaxBenefit mResponse;

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
    public void textTaxBenefits() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getTaxBenefitsResource()
                .get(new CallbackWebApi<TaxBenefit>() {
            @Override
            public void success(TaxBenefit taxBenefitsListResponse) {
                mResponse = taxBenefitsListResponse;
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

        TaxBenefit benefit = mResponse;
        assertEquals(Long.valueOf(29000), benefit.getTaxDeductiblePremium().getValue());
        assertEquals(Integer.valueOf(2), benefit.getTaxDeductiblePremium().getPrecision());
        assertEquals("CZK", benefit.getTaxDeductiblePremium().getCurrency());
        assertEquals(Long.valueOf(1190400), benefit.getRecommendedDeposit().getValue());
        assertEquals(Integer.valueOf(2), benefit.getRecommendedDeposit().getPrecision());
        assertEquals("CZK", benefit.getRecommendedDeposit().getCurrency());
        assertEquals("za předpokladu doplacení 4 splátek po 600 Kč v roce 2016", benefit.getRecommendedDepositText());
        PaymentTemplate template = benefit.getPaymentTemplate();
        assertEquals("7909689942", template.getSymbols().getVariableSymbol());
        assertEquals("1210230319", template.getReceiver().getNumber());
        assertEquals("0800", template.getReceiver().getBankCode());
    }
}

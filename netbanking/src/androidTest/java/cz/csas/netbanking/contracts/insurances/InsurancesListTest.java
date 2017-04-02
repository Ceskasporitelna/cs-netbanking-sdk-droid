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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 30.08.16.
 */
public class InsurancesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.list";
    private CountDownLatch mCountDownLatch;
    private InsuranceListResponse mResponse;

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
    public void testInsurancesList() {
        mNetbankingClient.getContractsResource().getInsurancesResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<InsuranceListResponse>() {
                    @Override
                    public void success(InsuranceListResponse insurancesListResponse) {
                        mResponse = insurancesListResponse;
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

        assertEquals(Long.valueOf(0), mResponse.getPageNumber());
        assertEquals(Long.valueOf(3), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());
        assertEquals(Long.valueOf(1), mResponse.getNextPage());

        assertEquals(1, mResponse.getItems().size());
        Insurance insurance = mResponse.getItems().get(0);

        assertEquals("3961D3F9E922EEE93E2581E896B34566645FE7E3", insurance.getId());
        assertEquals(InsuranceType.LIFE, insurance.getType());
        assertEquals("264", insurance.getProduct());
        assertEquals("Pojištění FLEXI", insurance.getProductI18N());
        assertEquals("Hana Bielčíková", insurance.getInsurancePolicyHolder());
        assertEquals("7009689942", insurance.getPolicyNumber());

        Life life = insurance.getLife();
        assertEquals(Long.valueOf(60000), life.getPremium().getValue());
        assertEquals(Integer.valueOf(2), life.getPremium().getPrecision());
        assertEquals("CZK", life.getPremium().getCurrency());
        assertEquals(TimeUtils.getPlainDate("2046-12-31"), life.getContractEndDate());
        assertEquals(TimeUtils.getPlainDate("2015-01-01"), life.getContractStartDate());
        assertEquals(Long.valueOf(0), life.getInsuredAmount().getValue());
        assertEquals(Integer.valueOf(2), life.getInsuredAmount().getPrecision());
        assertEquals("CZK", life.getInsuredAmount().getCurrency());
        assertEquals(Long.valueOf(0), life.getCurrentCapitalValue().getValue());
        assertEquals(Integer.valueOf(2), life.getCurrentCapitalValue().getPrecision());
        assertEquals("CZK", life.getCurrentCapitalValue().getCurrency());
        assertEquals(Interval.MONTHLY, life.getPremiumPaymentInterval());
        assertEquals(TimeUtils.getPlainDate("2015-01-15"), life.getLastPremiumDate());
        assertEquals(Long.valueOf(60000), life.getLastPremiumPaid().getValue());
        assertEquals(Integer.valueOf(2), life.getLastPremiumPaid().getPrecision());
        assertEquals("CZK", life.getLastPremiumPaid().getCurrency());
        assertEquals(TimeUtils.getPlainDate("0999-12-31"), life.getContractTerminationDate());
        assertEquals(Arrays.asList("contractTermination", "taxDeductible", "fundDistribution"), life.getFlags());
        assertEquals(InsuranceStatus.ACTIVE, insurance.getStatus());
    }
}

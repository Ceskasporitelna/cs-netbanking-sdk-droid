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
public class InsurancesListPaginationTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.list.pagination";
    private CountDownLatch mCountDownLatch1, mCountDownLatch2, mCountDownLatch3;
    private InsuranceListResponse mResponse1, mResponse2, mResponse3;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch1 = new CountDownLatch(1);
        mCountDownLatch2 = new CountDownLatch(1);
        mCountDownLatch3 = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testInsurancesListPagination() {
        mNetbankingClient.getContractsResource().getInsurancesResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<InsuranceListResponse>() {
                    @Override
                    public void success(InsuranceListResponse insurancesListResponse) {
                        mResponse1 = insurancesListResponse;
                        mCountDownLatch1.countDown();
                    }

                    @Override
                    public void failure(CsSDKError error) {
                        mCountDownLatch1.countDown();
                    }
                });

        try {
            mCountDownLatch1.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(0), mResponse1.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse1.getPageCount());
        assertEquals(Long.valueOf(1), mResponse1.getPageSize());
        assertEquals(Long.valueOf(1), mResponse1.getNextPage());

        assertEquals(1, mResponse1.getItems().size());
        Insurance insurance = mResponse1.getItems().get(0);

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

        mNetbankingClient.getContractsResource().getInsurancesResource().list(new PaginatedParameters(new Pagination(1, 1)),
                new CallbackWebApi<InsuranceListResponse>() {
                    @Override
                    public void success(InsuranceListResponse insurancesListResponse) {
                        mResponse2 = insurancesListResponse;
                        mCountDownLatch2.countDown();
                    }

                    @Override
                    public void failure(CsSDKError error) {
                        mCountDownLatch2.countDown();
                    }
                });

        try {
            mCountDownLatch2.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mResponse2.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse2.getPageCount());
        assertEquals(Long.valueOf(1), mResponse2.getPageSize());

        assertEquals(1, mResponse2.getItems().size());
        insurance = mResponse2.getItems().get(0);

        assertEquals("9B070F9C66A91D55A5D4E31F47B147444E651D36", insurance.getId());
        assertEquals(InsuranceType.LIFE, insurance.getType());
        assertEquals("267", insurance.getProduct());
        assertEquals("Pojištění FLEXI JUNIOR", insurance.getProductI18N());
        assertEquals("Hana Bielčíková", insurance.getInsurancePolicyHolder());
        assertEquals("5530446061", insurance.getPolicyNumber());

        life = insurance.getLife();
        assertEquals(Long.valueOf(40000), life.getPremium().getValue());
        assertEquals(Integer.valueOf(2), life.getPremium().getPrecision());
        assertEquals("CZK", life.getPremium().getCurrency());
        assertEquals(TimeUtils.getPlainDate("2033-01-31"), life.getContractEndDate());
        assertEquals(TimeUtils.getPlainDate("2011-02-01"), life.getContractStartDate());
        assertEquals(Long.valueOf(0), life.getInsuredAmount().getValue());
        assertEquals(Integer.valueOf(2), life.getInsuredAmount().getPrecision());
        assertEquals("CZK", life.getInsuredAmount().getCurrency());
        assertEquals(Long.valueOf(0), life.getCurrentCapitalValue().getValue());
        assertEquals(Integer.valueOf(2), life.getCurrentCapitalValue().getPrecision());
        assertEquals("CZK", life.getCurrentCapitalValue().getCurrency());
        assertEquals(Interval.MONTHLY, life.getPremiumPaymentInterval());
        assertEquals(TimeUtils.getPlainDate("2011-02-15"), life.getLastPremiumDate());
        assertEquals(Long.valueOf(40000), life.getLastPremiumPaid().getValue());
        assertEquals(Integer.valueOf(2), life.getLastPremiumPaid().getPrecision());
        assertEquals("CZK", life.getLastPremiumPaid().getCurrency());
        assertEquals(TimeUtils.getPlainDate("0999-12-31"), life.getContractTerminationDate());
        assertEquals(Arrays.asList("contractTermination"), life.getFlags());
        assertEquals(InsuranceStatus.ACTIVE, insurance.getStatus());

        mNetbankingClient.getContractsResource().getInsurancesResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<InsuranceListResponse>() {
                    @Override
                    public void success(InsuranceListResponse insurancesListResponse) {
                        mResponse3 = insurancesListResponse;
                        mCountDownLatch3.countDown();
                    }

                    @Override
                    public void failure(CsSDKError error) {
                        mCountDownLatch3.countDown();
                    }
                });

        try {
            mCountDownLatch3.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(0), mResponse3.getPageNumber());
        assertEquals(Long.valueOf(2), mResponse3.getPageCount());
        assertEquals(Long.valueOf(1), mResponse3.getPageSize());
        assertEquals(Long.valueOf(1), mResponse3.getNextPage());

        assertEquals(1, mResponse3.getItems().size());
        insurance = mResponse3.getItems().get(0);

        assertEquals("3961D3F9E922EEE93E2581E896B34566645FE7E3", insurance.getId());
        assertEquals(InsuranceType.LIFE, insurance.getType());
        assertEquals("264", insurance.getProduct());
        assertEquals("Pojištění FLEXI", insurance.getProductI18N());
        assertEquals("Hana Bielčíková", insurance.getInsurancePolicyHolder());
        assertEquals("7009689942", insurance.getPolicyNumber());

        life = insurance.getLife();
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

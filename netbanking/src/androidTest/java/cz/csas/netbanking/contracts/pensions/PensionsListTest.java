package cz.csas.netbanking.contracts.pensions;

import junit.framework.Assert;

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
public class PensionsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.pensions.list";
    private CountDownLatch mCountDownLatch;
    private PensionsListResponse mResponse;

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
    public void testPensionsList() {
        mNetbankingClient.getContractsResource().getPensionsResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<PensionsListResponse>() {
                    @Override
                    public void success(PensionsListResponse pensionsListResponse) {
                        mResponse = pensionsListResponse;
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
        assertEquals(Long.valueOf(2), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());
        assertEquals(Long.valueOf(1), mResponse.getNextPage());

        assertEquals(1, mResponse.getItems().size());
        Pension pension = mResponse.getItems().get(0);

        assertEquals(TimeUtils.getPlainDate("2015-12-01"), pension.getValidFrom());
        assertEquals(Long.valueOf(0), pension.getPaidBenefits().getValue());
        assertEquals(Integer.valueOf(2), pension.getPaidBenefits().getPrecision());
        assertEquals("CZK", pension.getPaidBenefits().getCurrency());
        assertEquals("Hana Bielčíková", pension.getOwner());
        assertEquals("E7DD68AA3FF4487AF75626F901761B071E72FFFC", pension.getId());
        assertEquals("700117447", pension.getAgreementNumber());
        assertEquals(PensionStatus.ACTIVE, pension.getStatus());
        assertEquals(Long.valueOf(767292), pension.getProductAccount().getAmount().getValue());
        assertEquals(Integer.valueOf(2), pension.getProductAccount().getAmount().getPrecision());
        assertEquals("CZK", pension.getProductAccount().getAmount().getCurrency());

        assertEquals("Doplňkové penzijní spoření", pension.getProductI18N());
        assertEquals("55", pension.getProduct());
        assertEquals("8152152602", pension.getBirthNumber());
        assertEquals(Double.valueOf(100), pension.getStrategy().getConservative());
        assertEquals(Boolean.valueOf(true), pension.getPensionAgreed().getOldAge());
        assertEquals(Boolean.valueOf(true), pension.getPensionAgreed().getDisability());
        assertEquals(Boolean.valueOf(true), pension.getContribution().getEmployer());
        assertEquals(Long.valueOf(30000), pension.getContribution().getParticipantAmount().getValue());
        assertEquals(Integer.valueOf(2), pension.getContribution().getParticipantAmount().getPrecision());
        assertEquals("CZK", pension.getContribution().getParticipantAmount().getCurrency());

        assertEquals(Boolean.valueOf(false), pension.getSupplementary().getMaxService());
        assertEquals(Boolean.valueOf(false), pension.getSupplementary().getOptService());

        assertEquals(2, pension.getBeneficiaries().size());
        assertEquals("Eliška Bielčíková", pension.getBeneficiaries().get(0).getName());
        assertEquals(TimeUtils.getPlainDate("2008-06-09"), pension.getBeneficiaries().get(0).getBirthDate());
        assertEquals("0806099999", pension.getBeneficiaries().get(0).getBirthNumber());
        assertEquals("CZ", pension.getBeneficiaries().get(0).getAddress().getCountry());
        assertEquals(Double.valueOf(50), pension.getBeneficiaries().get(0).getShare());
        Assert.assertEquals(Entitlement.TAKE_OVER, pension.getBeneficiaries().get(0).getEntitlement());

        assertEquals("Tomáš Bielčík", pension.getBeneficiaries().get(1).getName());
        assertEquals(TimeUtils.getPlainDate("2003-09-10"), pension.getBeneficiaries().get(1).getBirthDate());
        assertEquals("0309109999", pension.getBeneficiaries().get(1).getBirthNumber());
        assertEquals("CZ", pension.getBeneficiaries().get(1).getAddress().getCountry());
        assertEquals(Double.valueOf(50), pension.getBeneficiaries().get(1).getShare());
        assertEquals(Entitlement.TAKE_OVER, pension.getBeneficiaries().get(1).getEntitlement());

        assertEquals(PensionSubtype.SUPPLEMENTARY_SAVINGS, pension.getSubtype());
        assertEquals(Arrays.asList("accountQueryAllowed"), pension.getFlags());
    }
}

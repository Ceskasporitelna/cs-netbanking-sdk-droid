package cz.csas.netbanking.contracts.buildings;

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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class BuildingsListPaginationTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.buildings.list.pagination";
    private CountDownLatch mCountDownLatch1, mCountDownLatch2, mCountDownLatch3;
    private BuildingsListResponse mResponse1, mResponse2, mResponse3;

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
    public void testPagination() {
        mNetbankingClient.getContractsResource().getBuildingsResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<BuildingsListResponse>() {
                    @Override
                    public void success(BuildingsListResponse buildingsListResponse) {
                        mResponse1 = buildingsListResponse;
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
        BuildingsContract building = mResponse1.getItems().get(0);
        assertEquals("BCEF6B001FAE755D163A6CC9475E9FDFD9CD4A79", building.getId());
        assertEquals(BuildingAccountType.BUILD_SAVING, building.getType());
        assertEquals("280", building.getProduct());
        assertEquals("Stavební spoření", building.getProductI18N());
        assertEquals(Long.valueOf(169200), building.getBalance().getValue());
        assertEquals(Integer.valueOf(2), building.getBalance().getPrecision());
        assertEquals("CZK", building.getBalance().getCurrency());
        assertEquals(Arrays.asList("Hana Bielčíková"), building.getContractHolders());
        assertEquals(BuildingsContractStatus.ACTIVE, building.getStatus());

        BuildingSaving saving = building.getSaving();
        assertEquals(Long.valueOf(14100000), saving.getTargetAmount().getValue());
        assertEquals(Integer.valueOf(2), saving.getTargetAmount().getPrecision());
        assertEquals("CZK", saving.getTargetAmount().getCurrency());
        assertEquals(Long.valueOf(70500), saving.getAgreedMonthlySavings().getValue());
        assertEquals(Integer.valueOf(2), saving.getAgreedMonthlySavings().getPrecision());
        assertEquals("CZK", saving.getAgreedMonthlySavings().getCurrency());

        assertEquals(Arrays.asList("accountQueryAllowed"), building.getFlags());

        AccountNumber accountNo = building.getAccountno();
        assertEquals("51-1050445627", accountNo.getNumber());
        assertEquals("8060", accountNo.getBankCode());
        assertEquals("CZ", accountNo.getCountryCode());
        assertEquals("CZ1580600000511050445627", accountNo.getCzIban());
        assertEquals("GIBACZPX", accountNo.getCzBic());

        mNetbankingClient.getContractsResource().getBuildingsResource().list(new PaginatedParameters(new Pagination(1, 1)),
                new CallbackWebApi<BuildingsListResponse>() {
                    @Override
                    public void success(BuildingsListResponse buildingsListResponse) {
                        mResponse2 = buildingsListResponse;
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
        building = mResponse2.getItems().get(0);
        assertEquals("EC91C2D1B33D5A9E927C5B3EC509589818E292D1", building.getId());
        assertEquals(BuildingAccountType.BUILD_SAVING, building.getType());
        assertEquals("280", building.getProduct());
        assertEquals("Stavební spoření", building.getProductI18N());
        assertEquals(Long.valueOf(0), building.getBalance().getValue());
        assertEquals(Integer.valueOf(2), building.getBalance().getPrecision());
        assertEquals("CZK", building.getBalance().getCurrency());
        assertEquals(Arrays.asList("Hana Bielčíková"), building.getContractHolders());
        assertEquals(BuildingsContractStatus.ACTIVE, building.getStatus());

        saving = building.getSaving();
        assertEquals(Long.valueOf(18600000), saving.getTargetAmount().getValue());
        assertEquals(Integer.valueOf(2), saving.getTargetAmount().getPrecision());
        assertEquals("CZK", saving.getTargetAmount().getCurrency());
        assertEquals(Long.valueOf(93000), saving.getAgreedMonthlySavings().getValue());
        assertEquals(Integer.valueOf(2), saving.getAgreedMonthlySavings().getPrecision());
        assertEquals("CZK", saving.getAgreedMonthlySavings().getCurrency());

        assertEquals(Arrays.asList("accountQueryAllowed"), building.getFlags());

        accountNo = building.getAccountno();
        assertEquals("51-1050690710", accountNo.getNumber());
        assertEquals("8060", accountNo.getBankCode());
        assertEquals("CZ", accountNo.getCountryCode());
        assertEquals("CZ1780600000511050690710", accountNo.getCzIban());
        assertEquals("GIBACZPX", accountNo.getCzBic());

        mNetbankingClient.getContractsResource().getBuildingsResource().list(new PaginatedParameters(new Pagination(0, 1)),
                new CallbackWebApi<BuildingsListResponse>() {
                    @Override
                    public void success(BuildingsListResponse buildingsListResponse) {
                        mResponse3 = buildingsListResponse;
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
        building = mResponse3.getItems().get(0);
        assertEquals("BCEF6B001FAE755D163A6CC9475E9FDFD9CD4A79", building.getId());
        assertEquals(BuildingAccountType.BUILD_SAVING, building.getType());
        assertEquals("280", building.getProduct());
        assertEquals("Stavební spoření", building.getProductI18N());
        assertEquals(Long.valueOf(169200), building.getBalance().getValue());
        assertEquals(Integer.valueOf(2), building.getBalance().getPrecision());
        assertEquals("CZK", building.getBalance().getCurrency());
        assertEquals(Arrays.asList("Hana Bielčíková"), building.getContractHolders());
        assertEquals(BuildingsContractStatus.ACTIVE, building.getStatus());

        saving = building.getSaving();
        assertEquals(Long.valueOf(14100000), saving.getTargetAmount().getValue());
        assertEquals(Integer.valueOf(2), saving.getTargetAmount().getPrecision());
        assertEquals("CZK", saving.getTargetAmount().getCurrency());
        assertEquals(Long.valueOf(70500), saving.getAgreedMonthlySavings().getValue());
        assertEquals(Integer.valueOf(2), saving.getAgreedMonthlySavings().getPrecision());
        assertEquals("CZK", saving.getAgreedMonthlySavings().getCurrency());

        assertEquals(Arrays.asList("accountQueryAllowed"), building.getFlags());

        accountNo = building.getAccountno();
        assertEquals("51-1050445627", accountNo.getNumber());
        assertEquals("8060", accountNo.getBankCode());
        assertEquals("CZ", accountNo.getCountryCode());
        assertEquals("CZ1580600000511050445627", accountNo.getCzIban());
        assertEquals("GIBACZPX", accountNo.getCzBic());
    }
}

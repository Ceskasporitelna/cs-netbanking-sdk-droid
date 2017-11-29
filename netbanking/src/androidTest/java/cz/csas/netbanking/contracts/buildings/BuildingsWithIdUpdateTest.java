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
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.NetbankingTest;

import static org.junit.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class BuildingsWithIdUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.buildings.withId.update";
    private CountDownLatch mCountDownLatch;
    private BuildingsContractUpdateResponse mResponse;

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
    public void testBuildingsWithIdUpdate() {
        String id = "BCEF6B001FAE755D163A6CC9475E9FDFD9CD4A79";
        String alias = "test alias";
        BuildingsContractUpdateRequest request = new BuildingsContractUpdateRequest(alias);
        mNetbankingClient.getContractsResource().getBuildingsResource().withId(id).update(request, new CallbackWebApi<BuildingsContractUpdateResponse>() {
            @Override
            public void success(BuildingsContractUpdateResponse building) {
                mResponse = building;
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

        BuildingsContractUpdateResponse building = mResponse;
        assertEquals(id, building.getId());
        assertEquals("BCEF6B001FAE755D163A6CC9475E9FDFD9CD4A79", building.getId());
        assertEquals("test alias", building.getAlias());
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

        //FIXME unexpected travis crash on this line assertEquals(SigningState.NONE, building.signing().getSigningState());
    }
}

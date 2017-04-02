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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceServicesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.services.list";
    private CountDownLatch mCountDownLatch;
    private InsuranceServicesListResponse mResponse;

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
    public void testInsuranceServicesList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getServicesResource().list(new CallbackWebApi<InsuranceServicesListResponse>() {
            @Override
            public void success(InsuranceServicesListResponse response) {
                mResponse = response;
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

        List<InsuranceService> services = mResponse.getServices();
        assertEquals(1, services.size());
        InsuranceService service = services.get(0);

        assertEquals("1", service.getId());
        assertEquals("RISK_SPORTS", service.getGroup());
        assertEquals("RISK_SPORTS", service.getIconGroup());
        assertEquals(Double.valueOf(30), service.getAvailableDays());
        assertEquals(InsuranceServiceState.INACTIVE, service.getState());
        assertEquals("Rizikové sporty", service.getNameI18N());
        assertEquals("Zdarma pojištění libovolných 30 dnů v roce na aktivity jako horolezectví, bungee jumping, potápění nebo rafting. Stačí si jej aktivovat 2 hodiny před plánovanou aktivitou.",
                service.getDescriptionI18N());
    }
}

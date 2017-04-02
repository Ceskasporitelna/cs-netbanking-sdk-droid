package cz.csas.netbanking.contracts.buildings;

import org.junit.Test;

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
import cz.csas.netbanking.*;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 29.08.16.
 */
public class BuildingsWithIdServicesListTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "contracts.buildings.withId.services.list";
    private CountDownLatch mCountDownLatch;
    private cz.csas.netbanking.ServicesListResponse mResponse;

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
    public void testBuildingsWithIdServicesList() {
        String id = "BCEF6B001FAE755D163A6CC9475E9FDFD9CD4A79";
        String alias = "test alias";
        mNetbankingClient.getContractsResource().getBuildingsResource().withId(id).getServicesResource().list(
                new PaginatedParameters(new Pagination(0, 1)), new CallbackWebApi<ServicesListResponse>() {
            @Override
            public void success(ServicesListResponse response) {
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

        for(int i=0; i<mResponse.getServices().size(); ++i) {
            Service service = mResponse.getServices().get(i);
            switch(i) {
                case 0:
                    assertEquals("s54sdf756dfhm52879sdf23xd8744Fsdf5", service.getId());
                    assertEquals("Uver k stavebnimu sporeni", service.getNameI18N());
                    assertEquals("DEFAULT", service.getIconGroup());
                    break;

                case 1:
                    assertEquals("154dff756dfhm52879sdf23d845sd4f84f", service.getId());
                    assertEquals("Preklenovaci uver", service.getNameI18N());
                    assertEquals("DEFAULT", service.getIconGroup());
                    break;
            }
        }
    }
}

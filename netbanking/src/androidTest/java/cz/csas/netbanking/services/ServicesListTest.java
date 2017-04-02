package cz.csas.netbanking.services;

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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.Service;
import cz.csas.netbanking.ServicesListResponse;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class ServicesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "services.list";
    private CountDownLatch mCountDownLatch;
    private ServicesListResponse mResponse;

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
    public void testServicesList() {
        mNetbankingClient.getServicesResource().list(new PaginatedParameters(new Pagination(0, 1)), new CallbackWebApi<ServicesListResponse>() {
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

        assertEquals(Long.valueOf(0), mResponse.getPageNumber());
        assertEquals(Long.valueOf(1), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());

        List<Service> services = mResponse.getServices();
        assertEquals(1, services.size());

        Service service = services.get(0);
        assertEquals("EB8816A9C0E29A47F564E0BC2F30F8BB5A2FDB84", service.getId());
        assertEquals("SERVIS 24", service.getNameI18N());
        assertEquals("S24", service.getIconGroup());
    }
}

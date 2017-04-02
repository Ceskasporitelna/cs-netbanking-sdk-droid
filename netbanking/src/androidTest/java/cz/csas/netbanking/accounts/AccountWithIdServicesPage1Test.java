package cz.csas.netbanking.accounts;

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
 * The type Account with ID services test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountWithIdServicesPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.services.list.page1";
    private CountDownLatch mServicesSignal;
    private ServicesListResponse mServicesListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mServicesSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account with ID services page 1.
     */
    @Test
    public void testAccountWithIdServicesPage1() {

        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        PaginatedParameters parameters = new PaginatedParameters(new Pagination(1, 1));
        mNetbankingClient.getAccountsResource().withId(accountId).getServicesResource().list(parameters, new CallbackWebApi<ServicesListResponse>() {
            @Override
            public void success(ServicesListResponse servicesListResponse) {
                mServicesListResponse = servicesListResponse;
                mServicesSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mServicesSignal.countDown();
            }
        });

        try {
            mServicesSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mServicesListResponse.getPageNumber());
        assertEquals(Long.valueOf(4), mServicesListResponse.getPageCount());
        assertEquals(Long.valueOf(2), mServicesListResponse.getPageSize());
        assertEquals(Long.valueOf(2), mServicesListResponse.getNextPage());

        List<Service> services = mServicesListResponse.getServices();
        assertEquals(2, services.size());

        for (int i = 0; i < services.size(); ++i) {
            Service service = services.get(i);
            switch (i) {
                case 0:
                    assertEquals("5F66602F35A7D5A86066BC03A6882180BEF01CA3", service.getId());
                    assertEquals("Všechny platby v Kč", service.getNameI18N());
                    assertEquals("PAYMENTS", service.getIconGroup());
                    break;
                case 1:
                    assertEquals("A705433CFCD205249F4B816F2C63D309AEEFF4C9", service.getId());
                    assertEquals("Platební karta", service.getNameI18N());
                    assertEquals("CARDS", service.getIconGroup());
                    break;
            }
        }
    }
}

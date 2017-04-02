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
public class AccountWithIdServicesPage0Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.services.list.page0";
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
     * Test account with ID services page 0.
     */
    @Test
    public void testAccountWithIdServicesPage0() {

        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        PaginatedParameters parameters = new PaginatedParameters(new Pagination(0, 1));
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

        assertEquals(Long.valueOf(0), mServicesListResponse.getPageNumber());
        assertEquals(Long.valueOf(4), mServicesListResponse.getPageCount());
        assertEquals(Long.valueOf(2), mServicesListResponse.getPageSize());
        assertEquals(Long.valueOf(1), mServicesListResponse.getNextPage());

        List<Service> services = mServicesListResponse.getServices();
        assertEquals(2, services.size());

        for (int i = 0; i < services.size(); ++i) {
            Service service = services.get(i);
            switch (i) {
                case 0:
                    assertEquals("E878D16AD1A79FB60A520F48706C187AEFCA9D5D", service.getId());
                    assertEquals("2x výběr z bankomatů České spořitelny", service.getNameI18N());
                    assertEquals("CARDS", service.getIconGroup());
                    break;
                case 1:
                    assertEquals("3FB37388FC58076DEAD3DE282E075592A299B596", service.getId());
                    assertEquals("Platební karta", service.getNameI18N());
                    assertEquals("CARDS", service.getIconGroup());
                    break;
            }
        }
    }
}

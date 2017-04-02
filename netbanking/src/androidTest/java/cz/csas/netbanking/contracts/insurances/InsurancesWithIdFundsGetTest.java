package cz.csas.netbanking.contracts.insurances;

import org.junit.Test;

import java.util.Arrays;
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
import static junit.framework.Assert.assertSame;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancesWithIdFundsGetTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.funds.get";
    private CountDownLatch mCountDownLatch;
    private FundsListResponse mResponse;

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
    public void testInsurancesWithIdFundsGet() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getFundsResource().list(new CallbackWebApi<FundsListResponse>() {
            @Override
            public void success(FundsListResponse insuranceFundsListResponse) {
                mResponse = insuranceFundsListResponse;
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

        assertEquals(Long.valueOf(0), mResponse.getTotalInvestedAmount().getValue());
        assertEquals(Integer.valueOf(2), mResponse.getTotalInvestedAmount().getPrecision());
        assertEquals("CZK", mResponse.getTotalInvestedAmount().getCurrency());
        assertEquals(Arrays.asList("fundsChangeAllowed"), mResponse.getFlags());

        List<Fund> funds = mResponse.getItems();
        assertEquals(1, funds.size());
        Fund fund = funds.get(0);

        assertEquals("24", fund.getCode());
        assertEquals("Garantovaný fond pro běžné pojistné", fund.getName());
        assertEquals(Long.valueOf(0), fund.getInvestedAmount().getValue());
        assertEquals(Integer.valueOf(2), fund.getInvestedAmount().getPrecision());
        assertEquals("CZK", fund.getInvestedAmount().getCurrency());
        assertEquals(Double.valueOf(0), fund.getInvestedShare());
        assertEquals(Double.valueOf(100), fund.getAllocation());
    }
}

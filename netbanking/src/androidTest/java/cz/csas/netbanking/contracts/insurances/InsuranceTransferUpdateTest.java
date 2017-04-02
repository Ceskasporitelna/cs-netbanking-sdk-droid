package cz.csas.netbanking.contracts.insurances;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceTransferUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.transfer.update";
    private CountDownLatch mCountDownLatch;
    private InsuranceTransferUpdateResponse mResponse;

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
    public void testInsuranceTransfer() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        InsuranceTransferUpdateRequest request = new InsuranceTransferUpdateRequest(
                InsuranceTransferType.PAY_PREMIUM,
                new Amount(1500l, 2, "CZK"),
                new AccountNumber("2723000003", "0800")
        );

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getTransferResource()
                .update(request, new CallbackWebApi<InsuranceTransferUpdateResponse>() {
            @Override
            public void success(InsuranceTransferUpdateResponse insuranceTransferUpdateResponse) {
                mResponse = insuranceTransferUpdateResponse;
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

        assertEquals(SigningState.OPEN, mResponse.signing().getSigningState());
        assertEquals("160815138818340", mResponse.signing().getSignId());
    }
}

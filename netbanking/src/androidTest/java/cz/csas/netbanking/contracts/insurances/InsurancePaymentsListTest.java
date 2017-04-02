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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancePaymentsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.payments.list";
    private CountDownLatch mCountDownLatch;
    private ContractPaymentsListResponse mResponse;

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
    public void testInsurancePaymentsList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getPaymentsResource().list(new CallbackWebApi<ContractPaymentsListResponse>() {
            @Override
            public void success(ContractPaymentsListResponse contractPaymentsListResponse) {
                mResponse = contractPaymentsListResponse;
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

        assertEquals(Arrays.asList("extraDepositAllowed", "paymentExpected"), mResponse.getFlags());
        List<ContractPayment> payments = mResponse.getPayments();
        assertEquals(2, payments.size());

        for(int i=0; i<payments.size(); ++i) {
            ContractPayment payment = payments.get(i);
            switch (i) {
                case 0:
                    assertEquals("33", payment.getId());
                    assertEquals(PaymentType.FUTURE, payment.getType());
                    assertEquals(Long.valueOf(0), payment.getAmount().getValue());
                    assertEquals(Integer.valueOf(2), payment.getAmount().getPrecision());
                    assertEquals("CZK", payment.getAmount().getCurrency());
                    assertEquals(Long.valueOf(60000), payment.getRestToPay().getValue());
                    assertEquals(Integer.valueOf(2), payment.getRestToPay().getPrecision());
                    assertEquals("CZK", payment.getRestToPay().getCurrency());
                    assertEquals(Long.valueOf(60000), payment.getInstruction().getValue());
                    assertEquals(Integer.valueOf(2), payment.getInstruction().getPrecision());
                    assertEquals("CZK", payment.getInstruction().getCurrency());
                    assertEquals(TimeUtils.getPlainDate("2016-09-01"), payment.getInstructionFrom());
                    assertEquals(TimeUtils.getPlainDate("2016-09-30"), payment.getInstructionTo());
                    break;
                case 1:
                    assertEquals("32", payment.getId());
                    assertEquals(PaymentType.OVERDUE, payment.getType());
                    assertEquals(Long.valueOf(0), payment.getAmount().getValue());
                    assertEquals(Integer.valueOf(2), payment.getAmount().getPrecision());
                    assertEquals("CZK", payment.getAmount().getCurrency());
                    assertEquals(Long.valueOf(60000), payment.getRestToPay().getValue());
                    assertEquals(Integer.valueOf(2), payment.getRestToPay().getPrecision());
                    assertEquals("CZK", payment.getRestToPay().getCurrency());
                    assertEquals(Long.valueOf(60000), payment.getInstruction().getValue());
                    assertEquals(Integer.valueOf(2), payment.getInstruction().getPrecision());
                    assertEquals("CZK", payment.getInstruction().getCurrency());
                    assertEquals(TimeUtils.getPlainDate("2016-08-01"), payment.getInstructionFrom());
                    assertEquals(TimeUtils.getPlainDate("2016-08-31"), payment.getInstructionTo());
                    break;
            }
        }

    }
}

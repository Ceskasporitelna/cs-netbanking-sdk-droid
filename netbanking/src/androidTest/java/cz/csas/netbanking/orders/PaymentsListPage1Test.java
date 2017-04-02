package cz.csas.netbanking.orders;

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
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.list.page1";
    private CountDownLatch mOrdersSignal;
    private PaymentsListResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mOrdersSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testOrdersPaymentsListPage1() {
        PaymentsParameters parameters = new PaymentsParameters(new Pagination(1, 1), null);

        mNetbankingClient.getOrdersResource().getPaymentsResource().list(parameters, new CallbackWebApi<PaymentsListResponse>() {
            @Override
            public void success(PaymentsListResponse paymentsListResponse) {
                mResponse = paymentsListResponse;
                mOrdersSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mOrdersSignal.countDown();
            }
        });

        try {
            mOrdersSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mResponse.getPageNumber());
        assertEquals(Long.valueOf(7), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());
        assertEquals(Long.valueOf(2), mResponse.getNextPage());

        Payment payment = mResponse.getPayments().get(0);
        Amount amount = payment.getAmount();
        AccountNumber receiver = payment.getReceiver();
        AccountNumber sender = payment.getSender();
        SigningObject signingObject = payment.signing();
        List<String> expectedFlags = Arrays.asList(
                "redoable",
                "own_transfer");

        assertEquals("T4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE_1XZ1XZO5o0VZB", payment.getId());
        assertEquals(null, payment.getAdditionalInfo().getText4x35());
        assertEquals(Long.valueOf(100000), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());
        assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
        assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
        assertEquals(TimeUtils.getISO8601Date("2016-03-22T00:00:00+01:00"), payment.getCzOrderingDate());
        assertEquals(TimeUtils.getISO8601Date("2016-03-22T00:00:00+01:00"), payment.getExecutionDate());
        assertEquals(expectedFlags, payment.getFlags());
        assertEquals(PaymentCategory.OWN_TRANSFER, payment.getOrderCategory());
        assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

        assertEquals("428602109", receiver.getNumber());
        assertEquals("0800", receiver.getBankCode());
        assertEquals("CZ", receiver.getCountryCode());
        assertEquals("CZ6408000000000428602109", receiver.getCzIban());
        assertEquals("GIBACZPX", receiver.getCzBic());

        assertEquals("R1EZG2CY", payment.getReferenceId());

        assertEquals("2059930033", sender.getNumber());
        assertEquals("0800", sender.getBankCode());
        assertEquals("CZ", sender.getCountryCode());
        assertEquals("CZ1208000000002059930033", sender.getCzIban());
        assertEquals("GIBACZPX", sender.getCzBic());

        assertEquals(SigningState.NONE, signingObject.getSigningState());
        assertEquals(PaymentState.CLOSED, payment.getState());
        assertEquals(PaymentStateDetail.FIN, payment.getStateDetail());
        assertEquals(Boolean.valueOf(true), payment.getStateOk());
        assertEquals(TimeUtils.getPlainDate("2016-03-22"), payment.getTransferDate());
    }
}

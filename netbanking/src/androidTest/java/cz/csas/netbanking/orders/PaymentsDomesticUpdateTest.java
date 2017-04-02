package cz.csas.netbanking.orders;

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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentsDomesticUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.domestic.update";
    private CountDownLatch mOrdersSignal;
    private DomesticPaymentUpdateResponse mDomesticOrderResponse;

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
    public void testPaymentsDomesticUpdate() {

        String orderId = "1154226597";
        DomesticPaymentUpdateRequest request = new DomesticPaymentUpdateRequest.Builder()
                .setSenderName("Vrba")
                .setSender(new AccountNumber("2059930033", "0800"))
                .setReceiverName("Vojtíšková Alena")
                .setReceiver(new AccountNumber("2328489013", "0800"))
                .setAmount(new Amount(110L, 2, "CZK"))
                .build();

        mNetbankingClient.getOrdersResource().getPaymentsResource().getDomesticResource().withId(orderId)
                .update(request, new CallbackWebApi<DomesticPaymentUpdateResponse>() {
            @Override
            public void success(DomesticPaymentUpdateResponse domesticOrderResponse) {
                mDomesticOrderResponse = domesticOrderResponse;
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

        Payment payment = mDomesticOrderResponse;

        assertEquals(orderId, payment.getId());
        assertEquals(Long.valueOf(110), payment.getAmount().getValue());
        assertEquals(Integer.valueOf(2), payment.getAmount().getPrecision());
        assertEquals("CZK", payment.getAmount().getCurrency());
        assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
        assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T10:33:41+01:00"), payment.getCzOrderingDate());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T00:00:00+01:00"), payment.getExecutionDate());
        assertEquals(Arrays.asList("editable", "deletable"), payment.getFlags());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T10:33:41+01:00"), payment.getModificationDate());
        assertEquals(PaymentCategory.DOMESTIC, payment.getOrderCategory());
        assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

        assertEquals("2328489013", payment.getReceiver().getNumber());
        assertEquals("0800", payment.getReceiver().getBankCode());
        assertEquals("CZ", payment.getReceiver().getCountryCode());
        assertEquals("CZ5908000000002328489013", payment.getReceiver().getCzIban());
        assertEquals("GIBACZPX", payment.getReceiver().getCzBic());
        assertEquals("Vojtíšková Alena", payment.getReceiverName());

        assertEquals("2059930033", payment.getSender().getNumber());
        assertEquals("0800", payment.getSender().getBankCode());
        assertEquals("CZ", payment.getSender().getCountryCode());
        assertEquals("CZ1208000000002059930033", payment.getSender().getCzIban());
        assertEquals("GIBACZPX", payment.getSender().getCzBic());
        assertEquals("Vrba", payment.getSenderName());

        assertEquals("8e6b2185f13bf2fcd3dc6e864d2fd1142813f3198d3633501aa7244a481d5458", payment.signing().getSignId());
        assertEquals(SigningState.OPEN, payment.signing().getSigningState());

        assertEquals(PaymentState.OPEN, payment.getState());
        assertEquals(PaymentStateDetail.OPN, payment.getStateDetail());
        assertEquals(Boolean.valueOf(true), payment.getStateOk());
        assertEquals(TimeUtils.getPlainDate("2016-03-21"), payment.getTransferDate());
    }
}

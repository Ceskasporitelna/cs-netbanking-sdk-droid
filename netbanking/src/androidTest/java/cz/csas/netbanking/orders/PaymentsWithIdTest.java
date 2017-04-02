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
public class PaymentsWithIdTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.withId.get";
    private CountDownLatch mOrdersSignal;
    private Payment mPayment;

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
    public void testPaymentWithId() {
        String paymentId = "1023464260";
        mNetbankingClient.getOrdersResource().getPaymentsResource().withId(paymentId).get(new CallbackWebApi<Payment>() {
            @Override
            public void success(Payment payment) {
                mPayment = payment;
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

        Amount amount = mPayment.getAmount();
        AccountNumber receiver = mPayment.getReceiver();
        AccountNumber sender = mPayment.getSender();
        SigningObject signingObject = mPayment.signing();
        List<String> expectedFlags = Arrays.asList(
                "editable",
                "deletable",
                "signable",
                "own_transfer");
        
        assertEquals("1023464260", mPayment.getId());
        assertEquals(null, mPayment.getAdditionalInfo().getText4x35());
        assertEquals(Long.valueOf(200000), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());
        assertEquals(ApplicationId.GEORGE, mPayment.getApplicationId());
        assertEquals(ChannelId.NET_BANKING, mPayment.getChannelId());
        assertEquals(TimeUtils.getISO8601Date("2016-03-20T00:00:00+01:00"), mPayment.getCzOrderingDate());
        assertEquals(TimeUtils.getISO8601Date("2016-03-20T00:00:00+01:00"), mPayment.getExecutionDate());
        assertEquals(expectedFlags, mPayment.getFlags());
        assertEquals(TimeUtils.getISO8601Date("2016-03-20T18:16:04+01:00"), mPayment.getModificationDate());
        assertEquals(PaymentCategory.OWN_TRANSFER, mPayment.getOrderCategory());
        assertEquals(PaymentOrderType.PAYMENT_OUT, mPayment.getOrderType());

        assertEquals("428602109", receiver.getNumber());
        assertEquals("0800", receiver.getBankCode());
        assertEquals("CZ", receiver.getCountryCode());
        assertEquals("CZ6408000000000428602109", receiver.getCzIban());
        assertEquals("GIBACZPX", receiver.getCzBic());

        assertEquals("2059930033", sender.getNumber());
        assertEquals("0800", sender.getBankCode());
        assertEquals("CZ", sender.getCountryCode());
        assertEquals("CZ1208000000002059930033", sender.getCzIban());
        assertEquals("GIBACZPX", sender.getCzBic());

        assertEquals("Aleš Vrba", mPayment.getSenderName());
        assertEquals(SigningState.OPEN, signingObject.getSigningState());
        assertEquals("1b20fd5dd9e41f0b0c08a3ebcafedcd1e2fe64ae937e0dc8a1e7f946b3d6b0f", signingObject.getSignId());
        assertEquals(PaymentState.OPEN, mPayment.getState());
        assertEquals(PaymentStateDetail.OPN, mPayment.getStateDetail());
        assertEquals(Boolean.valueOf(true), mPayment.getStateOk());
        assertEquals(TimeUtils.getPlainDate("2016-03-21"), mPayment.getTransferDate());
        assertEquals("Vrba Aleš", mPayment.getReceiverName());
    }
}

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
import cz.csas.cscore.webapi.Sort;
import cz.csas.cscore.webapi.SortDirection;
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
public class PaymentsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.list";
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
    public void testOrdersPaymentsList() {
        PaymentsParameters parameters = new PaymentsParameters(null,
                Sort.by(PaymentsSortableFields.TRANSFER_DATE, SortDirection.ASCENDING));

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

        assertEquals(Long.valueOf(0), mResponse.getPageNumber());
        assertEquals(Long.valueOf(1), mResponse.getPageCount());
        assertEquals(Long.valueOf(2), mResponse.getPageSize());

        List<Payment> payments = mResponse.getPayments();

        for (int i = 0; i < payments.size(); ++i) {
            Payment payment = payments.get(i);
            Amount amount = payment.getAmount();
            AccountNumber receiver = payment.getReceiver();
            AccountNumber sender = payment.getSender();
            SigningObject signingObject = payment.signing();
            List<String> expectedFlags = Arrays.asList(
                    "editable",
                    "deletable",
                    "signable",
                    "own_transfer");

            switch (i) {
                case 0:
                    assertEquals("1023464260", payment.getId());
                    assertEquals(null, payment.getAdditionalInfo().getText4x35());
                    assertEquals(Long.valueOf(200000), amount.getValue());
                    assertEquals(Integer.valueOf(2), amount.getPrecision());
                    assertEquals("CZK", amount.getCurrency());
                    assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
                    assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
                    assertEquals(TimeUtils.getISO8601Date("2016-03-20T00:00:00+01:00"), payment.getCzOrderingDate());
                    assertEquals(TimeUtils.getISO8601Date("2016-03-20T00:00:00+01:00"), payment.getExecutionDate());
                    assertEquals(expectedFlags, payment.getFlags());
                    assertEquals(TimeUtils.getISO8601Date("2016-03-20T18:16:04+01:00"), payment.getModificationDate());
                    assertEquals(PaymentCategory.OWN_TRANSFER, payment.getOrderCategory());
                    assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

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

                    assertEquals("Aleš Vrba", payment.getSenderName());
                    assertEquals(SigningState.OPEN, signingObject.getSigningState());
                    assertEquals("1b20fd5dd9e41f0b0c08a3ebcafedcd1e2fe64ae937e0dc8a1e7f946b3d6b0f", signingObject.getSignId());
                    assertEquals(PaymentState.OPEN, payment.getState());
                    assertEquals(PaymentStateDetail.OPN, payment.getStateDetail());
                    assertEquals(Boolean.valueOf(true), payment.getStateOk());
                    assertEquals(TimeUtils.getPlainDate("2016-03-21"), payment.getTransferDate());
                    assertEquals("Vrba Aleš", payment.getReceiverName());

                    break;
                case 1:
                    assertEquals("1719856390", payment.getId());
                    assertEquals(Arrays.asList("Vlastní převod"), payment.getAdditionalInfo().getText4x35());
                    assertEquals(Long.valueOf(12000), amount.getValue());
                    assertEquals(Integer.valueOf(2), amount.getPrecision());
                    assertEquals("CZK", amount.getCurrency());
                    assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
                    assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
                    assertEquals(TimeUtils.getISO8601Date("2016-03-20T00:00:00+01:00"), payment.getCzOrderingDate());
                    assertEquals(expectedFlags, payment.getFlags());
                    assertEquals(TimeUtils.getISO8601Date("2016-03-20T18:13:40+01:00"), payment.getModificationDate());
                    assertEquals(PaymentCategory.OWN_TRANSFER, payment.getOrderCategory());
                    assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

                    assertEquals("2059930033", receiver.getNumber());
                    assertEquals("0800", receiver.getBankCode());
                    assertEquals("CZ", receiver.getCountryCode());
                    assertEquals("CZ1208000000002059930033", receiver.getCzIban());
                    assertEquals("GIBACZPX", receiver.getCzBic());

                    assertEquals("3668601379", sender.getNumber());
                    assertEquals("0800", sender.getBankCode());
                    assertEquals("CZ", sender.getCountryCode());
                    assertEquals("CZ7308000000003668601379", sender.getCzIban());
                    assertEquals("GIBACZPX", sender.getCzBic());

                    assertEquals("Spořicí účet k Osobnímu kontu", payment.getSenderName());
                    assertEquals(SigningState.OPEN, signingObject.getSigningState());
                    assertEquals("ff7d933db8049afd048712d445429218d485b63e5320acac31bd4289f2c8bbba", signingObject.getSignId());
                    assertEquals(PaymentState.OPEN, payment.getState());
                    assertEquals(PaymentStateDetail.OPN, payment.getStateDetail());
                    assertEquals(Boolean.valueOf(true), payment.getStateOk());
                    assertEquals(TimeUtils.getPlainDate("2016-03-21"), payment.getTransferDate());
                    assertEquals("Aleš Vrba", payment.getReceiverName());
                    break;
            }
        }
    }
}

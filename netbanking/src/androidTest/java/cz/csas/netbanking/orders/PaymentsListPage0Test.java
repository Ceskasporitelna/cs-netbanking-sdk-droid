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
public class PaymentsListPage0Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "payments.list.page0";
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
    public void testOrdersPaymentsListPage0() {
        PaymentsParameters parameters = new PaymentsParameters(new Pagination(0, 1), null);

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
        assertEquals(Long.valueOf(7), mResponse.getPageCount());
        assertEquals(Long.valueOf(1), mResponse.getPageSize());
        assertEquals(Long.valueOf(1), mResponse.getNextPage());

        Payment payment = mResponse.getPayments().get(0);
        Amount amount = payment.getAmount();
        AccountNumber receiver = payment.getReceiver();
        AccountNumber sender = payment.getSender();
        SigningObject signingObject = payment.signing();
        List<String> expectedFlags = Arrays.asList(
                "editable",
                "deletable",
                "signable",
                "domestic");

        assertEquals("1154226597", payment.getId());
        assertEquals(null, payment.getAdditionalInfo().getText4x35());
        assertEquals(Long.valueOf(110), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());
        assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
        assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T00:00:00+01:00"), payment.getCzOrderingDate());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T00:00:00+01:00"), payment.getExecutionDate());
        assertEquals(expectedFlags, payment.getFlags());
        assertEquals(TimeUtils.getISO8601Date("2016-03-21T10:33:41+01:00"), payment.getModificationDate());
        assertEquals(PaymentCategory.DOMESTIC, payment.getOrderCategory());
        assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

        assertEquals("2328489013", receiver.getNumber());
        assertEquals("0800", receiver.getBankCode());
        assertEquals("CZ", receiver.getCountryCode());
        assertEquals("CZ5908000000002328489013", receiver.getCzIban());
        assertEquals("GIBACZPX", receiver.getCzBic());

        assertEquals("2059930033", sender.getNumber());
        assertEquals("0800", sender.getBankCode());
        assertEquals("CZ", sender.getCountryCode());
        assertEquals("CZ1208000000002059930033", sender.getCzIban());
        assertEquals("GIBACZPX", sender.getCzBic());

        assertEquals("Vrba", payment.getSenderName());
        assertEquals(SigningState.OPEN, signingObject.getSigningState());
        assertEquals("853eec470df3a40cb58f3504b956285cf59c3ca9c835d277c6706e7cd2b15e35", signingObject.getSignId());
        assertEquals(PaymentState.OPEN, payment.getState());
        assertEquals(PaymentStateDetail.OPN, payment.getStateDetail());
        assertEquals(Boolean.valueOf(true), payment.getStateOk());
        assertEquals(TimeUtils.getPlainDate("2016-03-23"), payment.getTransferDate());
        assertEquals("Vojtíšková Alena", payment.getReceiverName());
    }
}

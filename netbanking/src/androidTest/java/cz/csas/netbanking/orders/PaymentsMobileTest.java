package cz.csas.netbanking.orders;

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
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentsMobileTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "payments.mobile.create";
    private CountDownLatch mOrdersSignal;
    private MobilePaymentResponse mResponse;

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
    public void testMobilePayments() {
        MobilePaymentRequest request = new MobilePaymentRequest.Builder()
                .setPaymentType(MobilePaymentType.VODAFONE_PAYMENT)
                .setPhoneNumber("777952341")
                .setSender(new MobilePaymentSender("2059930033", "0800", "CZ", "CZ1208000000002059930033", "GIBACZPX"))
                .setAmount(new Amount(3000L, 0, "CZK"))
                .setConfirmationPhoneNumber("777952341")
                .build();

        mNetbankingClient.getOrdersResource().getPaymentsResource().getMobileResource().create(request, new CallbackWebApi<MobilePaymentResponse>() {
            @Override
            public void success(MobilePaymentResponse mobilePaymentResponse) {
                mResponse = mobilePaymentResponse;
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

        assertEquals(MobilePaymentType.VODAFONE_PAYMENT, mResponse.getPaymentType());
        assertEquals("777952341", mResponse.getPhoneNumber());
        assertEquals("2059930033", mResponse.getSender().getNumber());
        assertEquals("0800", mResponse.getSender().getBankCode());
        assertEquals("CZ", mResponse.getSender().getCountryCode());
        assertEquals("CZ1208000000002059930033", mResponse.getSender().getIban());
        assertEquals("GIBACZPX", mResponse.getSender().getBic());
        assertEquals(Long.valueOf(300000), mResponse.getAmount().getValue());
        assertEquals(Integer.valueOf(2), mResponse.getAmount().getPrecision());
        assertEquals("CZK", mResponse.getAmount().getCurrency());
        assertEquals("777952341", mResponse.getConfirmationPhoneNumber());
        assertEquals(SigningState.OPEN, mResponse.signing().getSigningState());
        assertEquals("1671744209", mResponse.signing().getSignId());
    }
}

package cz.csas.netbanking.signing;

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
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.cscore.webapi.signing.FilledSigningObject;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.cscore.webapi.signing.TacSigningProcess;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.orders.ApplicationId;
import cz.csas.netbanking.orders.ChannelId;
import cz.csas.netbanking.orders.DomesticPaymentUpdateRequest;
import cz.csas.netbanking.orders.DomesticPaymentUpdateResponse;
import cz.csas.netbanking.orders.Payment;
import cz.csas.netbanking.orders.PaymentCategory;
import cz.csas.netbanking.orders.PaymentOrderType;
import cz.csas.netbanking.orders.PaymentState;
import cz.csas.netbanking.orders.PaymentStateDetail;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 24.05.16.
 */
public class TacPaymentsDomesticUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "signing.tac.payments.domestic.withId.update";
    private CountDownLatch mRequestSignal;
    private CountDownLatch mSignSignalGET;
    private CountDownLatch mSignSignalPOST;
    private CountDownLatch mSignSignalPUT;
    private DomesticPaymentUpdateResponse mResponse;
    private FilledSigningObject mFilledSigningObject;
    private TacSigningProcess mTacSigningProcess;

    @Override
    public void setUp() {
        super.setUp();
        mRequestSignal = new CountDownLatch(1);
        mSignSignalGET = new CountDownLatch(1);
        mSignSignalPOST = new CountDownLatch(1);
        mSignSignalPUT = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testSigning() {
        String requestId = "160429968927553";
        DomesticPaymentUpdateRequest request = new DomesticPaymentUpdateRequest.Builder()
                .setSenderName("Vrba")
                .setSender(new AccountNumber("2059930033", "0800"))
                .setReceiverName("Vojtíšková Alena")
                .setReceiver(new AccountNumber("2328489013", "0800"))
                .setAmount(new Amount(110L, 2, "CZK"))
                .build();

        mNetbankingClient.getOrdersResource().getPaymentsResource().getDomesticResource().withId(requestId)
                .update(request, new CallbackWebApi<DomesticPaymentUpdateResponse>() {
                    @Override
                    public void success(DomesticPaymentUpdateResponse domesticPaymentUpdateResponse) {
                        mResponse = domesticPaymentUpdateResponse;
                        mRequestSignal.countDown();
                    }

                    @Override
                    public void failure(CsSDKError error) {
                        mRequestSignal.countDown();
                    }
                });

        try {
            mRequestSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Payment payment = mResponse;
        SigningObject signingObject = mResponse.signing();

        assertEquals("160429968927553", payment.getId());
        assertEquals(Long.valueOf(110), payment.getAmount().getValue());
        assertEquals(Integer.valueOf(2), payment.getAmount().getPrecision());
        assertEquals("CZK", payment.getAmount().getCurrency());
        assertEquals(ApplicationId.GEORGE, payment.getApplicationId());
        assertEquals(ChannelId.NET_BANKING, payment.getChannelId());
        assertEquals(TimeUtils.getISO8601Date("2016-04-14T15:39:48+02:00"), payment.getCzOrderingDate());
        assertEquals(TimeUtils.getISO8601Date("2016-04-14T00:00:00+02:00"), payment.getExecutionDate());
        assertEquals(Arrays.asList("editable", "deletable"), payment.getFlags());
        assertEquals(TimeUtils.getISO8601Date("2016-04-14T15:39:48+02:00"), payment.getModificationDate());
        assertEquals(PaymentCategory.DOMESTIC, payment.getOrderCategory());
        assertEquals(PaymentOrderType.PAYMENT_OUT, payment.getOrderType());

        assertEquals("2059930033", payment.getReceiver().getNumber());
        assertEquals("0800", payment.getReceiver().getBankCode());
        assertEquals("CZ", payment.getReceiver().getCountryCode());
        assertEquals("CZ1208000000002059930033", payment.getReceiver().getCzIban());
        assertEquals("GIBACZPX", payment.getReceiver().getCzBic());
        assertEquals("Vojtíšková", payment.getReceiverName());

        assertEquals("2328489013", payment.getSender().getNumber());
        assertEquals("0800", payment.getSender().getBankCode());
        assertEquals("CZ", payment.getSender().getCountryCode());
        assertEquals("CZ5908000000002328489013", payment.getSender().getCzIban());
        assertEquals("GIBACZPX", payment.getSender().getCzBic());
        assertEquals("Vrba", payment.getSenderName());

        assertEquals("ba739d14b5371c959346c4607d37ad99ae05c607576e5d803f052224575e9e6c", payment.signing().getSignId());
        assertEquals(SigningState.OPEN, payment.signing().getSigningState());

        assertEquals(PaymentState.OPEN, payment.getState());
        assertEquals(PaymentStateDetail.OPN, payment.getStateDetail());
        assertEquals(Boolean.valueOf(true), payment.getStateOk());
        assertEquals(TimeUtils.getPlainDate("2016-04-14"), payment.getTransferDate());


        // GET signing info

        signingObject.getInfo(new CallbackWebApi<FilledSigningObject>() {
            @Override
            public void success(FilledSigningObject filledSigningObject) {
                mFilledSigningObject = filledSigningObject;
                mSignSignalGET.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mSignSignalGET.countDown();
            }
        });

        try {
            mSignSignalGET.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(mFilledSigningObject);
        assertEquals(AuthorizationType.TAC, mFilledSigningObject.getAuthorizationType());
        assertEquals(Arrays.asList(Arrays.asList(AuthorizationType.TAC)), mFilledSigningObject.getScenarios());
        assertEquals(SigningState.OPEN, mFilledSigningObject.getSigningState());
        assertEquals("ba739d14b5371c959346c4607d37ad99ae05c607576e5d803f052224575e9e6c", mFilledSigningObject.getSignId());

        // POST signing request

        mFilledSigningObject.startSigningWithTac(new CallbackWebApi<TacSigningProcess>() {
            @Override
            public void success(TacSigningProcess tacSigningProcess) {
                mTacSigningProcess = tacSigningProcess;
                mSignSignalPOST.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mSignSignalPOST.countDown();
            }
        });

        try {
            mSignSignalPOST.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(mTacSigningProcess);


        // Finish signing

        mTacSigningProcess.finishSigning("00000000", new CallbackWebApi<SigningObject>() {
            @Override
            public void success(SigningObject signingObject) {
                mSignSignalPUT.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mSignSignalPUT.countDown();
            }
        });

        try {
            mSignSignalPUT.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(mFilledSigningObject);
        assertEquals("ba739d14b5371c959346c4607d37ad99ae05c607576e5d803f052224575e9e6c", mFilledSigningObject.getSignId());
        assertEquals(SigningState.DONE, mFilledSigningObject.getSigningState());
        assertEquals(SigningState.DONE, mResponse.signing().getSigningState());
    }
}

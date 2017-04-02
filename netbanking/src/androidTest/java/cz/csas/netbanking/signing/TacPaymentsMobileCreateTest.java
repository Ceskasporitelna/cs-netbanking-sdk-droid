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
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.cscore.webapi.signing.FilledSigningObject;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.cscore.webapi.signing.TacSigningProcess;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.orders.MobilePaymentRequest;
import cz.csas.netbanking.orders.MobilePaymentResponse;
import cz.csas.netbanking.orders.MobilePaymentSender;
import cz.csas.netbanking.orders.MobilePaymentType;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 24.05.16.
 */
public class TacPaymentsMobileCreateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "signing.tac.orders.payments.mobile.create";
    private CountDownLatch mRequestSignal;
    private CountDownLatch mSignSignalGET;
    private CountDownLatch mSignSignalPOST;
    private CountDownLatch mSignSignalPUT;
    private MobilePaymentResponse mResponse;
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

    /**
     * Test signing.
     */
    @Test
    public void testSigning() {

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

        SigningObject signingObject = mResponse.signing();
        assertEquals(SigningState.OPEN, signingObject.getSigningState());
        assertEquals("1671744209", signingObject.getSignId());

        assertEquals(MobilePaymentType.VODAFONE_PAYMENT, mResponse.getPaymentType());
        assertEquals("777952341", mResponse.getPhoneNumber());
        assertEquals("2059930033", mResponse.getSender().getNumber());
        assertEquals("0800", mResponse.getSender().getBankCode());
        assertEquals("CZ", mResponse.getSender().getCountryCode());
        assertEquals("CZ1208000000002059930033", mResponse.getSender().getIban());
        assertEquals("GIBACZPX", mResponse.getSender().getBic());
        assertEquals(Long.valueOf(300000L), mResponse.getAmount().getValue());
        assertEquals(Integer.valueOf(2), mResponse.getAmount().getPrecision());
        assertEquals("CZK", mResponse.getAmount().getCurrency());
        assertEquals("777952341", mResponse.getConfirmationPhoneNumber());


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
        assertEquals("1671744209", mFilledSigningObject.getSignId());


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
        assertEquals("1671744209", mFilledSigningObject.getSignId());
        assertEquals(SigningState.DONE, mFilledSigningObject.getSigningState());
        assertEquals(SigningState.DONE, mResponse.signing().getSigningState());
    }
}

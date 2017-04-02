package cz.csas.netbanking.signing;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.error.CsSigningError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.cscore.webapi.signing.FilledSigningObject;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.cscore.webapi.signing.TacSigningProcess;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.cards.CardLimit;
import cz.csas.netbanking.cards.CardLimitPeriod;
import cz.csas.netbanking.cards.CardLimitType;
import cz.csas.netbanking.cards.ChangeCardLimitsRequest;
import cz.csas.netbanking.cards.ChangeCardLimitsResponse;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 24.05.16.
 */
public class TacCardsLimitsUpdateOTPTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "signing.tac.cards.limits.update.OTP.invalid";
    private CountDownLatch mRequestSignal;
    private CountDownLatch mSignSignalGET;
    private CountDownLatch mSignSignalPOST;
    private CountDownLatch mSignSignalPUT;
    private ChangeCardLimitsResponse mResponse;
    private FilledSigningObject mFilledSigningObject;
    private TacSigningProcess mTacSigningProcess;
    private CsSigningError mError;

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
        String cardId = "3FB37388FC58076DEAD3DE282E075592A299B596";
        ChangeCardLimitsRequest request = new ChangeCardLimitsRequest(Arrays.asList(
                new CardLimit(CardLimitType.ATM, CardLimitPeriod.FIVE_D, new Amount(1100000L, 2, "CZK"), null, null, null)), null);
        mNetbankingClient.getCardsResource().withId(cardId).getLimitsResource().update(request, new CallbackWebApi<ChangeCardLimitsResponse>() {
            @Override
            public void success(ChangeCardLimitsResponse changeCardLimitsResponse) {
                mResponse = changeCardLimitsResponse;
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
        assertEquals("1445623881", signingObject.getSignId());

        List<CardLimit> limits = mResponse.getLimits();
        assertEquals(3, limits.size());
        for (CardLimit limit : limits) {
            switch (limit.getLimitType()) {
                case ATM:
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(1100000), limit.getLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getLimit().getPrecision());
                    assertEquals("CZK", limit.getLimit().getCurrency());
                    assertEquals(Long.valueOf(7000000), limit.getBankLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getBankLimit().getPrecision());
                    assertEquals("CZK", limit.getBankLimit().getCurrency());
                    break;
                case POS:
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(5000000), limit.getLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getLimit().getPrecision());
                    assertEquals("CZK", limit.getLimit().getCurrency());
                    assertEquals(Long.valueOf(50000000), limit.getBankLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getBankLimit().getPrecision());
                    assertEquals("CZK", limit.getBankLimit().getCurrency());
                    break;
                case INTERNET:
                    assertEquals(CardLimitPeriod.ONE_D, limit.getLimitPeriod());
                    assertEquals(Long.valueOf(500000), limit.getLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getLimit().getPrecision());
                    assertEquals("CZK", limit.getLimit().getCurrency());
                    assertEquals(Long.valueOf(50000000), limit.getBankLimit().getValue());
                    assertEquals(Integer.valueOf(2), limit.getBankLimit().getPrecision());
                    assertEquals("CZK", limit.getBankLimit().getCurrency());
                    break;
            }
        }


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
        assertEquals("1445623881", mFilledSigningObject.getSignId());


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
            mSignSignalPOST.await(620, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertNotNull(mTacSigningProcess);


        // Finish signing

        mTacSigningProcess.finishSigning("12345678", new CallbackWebApi<SigningObject>() {
            @Override
            public void success(SigningObject signingObject) {
                mSignSignalPUT.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mError = (CsSigningError) error;
                mSignSignalPUT.countDown();
            }
        });

        try {
            mSignSignalPUT.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(SigningState.OPEN, mFilledSigningObject.getSigningState());
        assertNotNull(mError);
        assertEquals(CsSigningError.Kind.INVALID_OTP, mError.getKind());
    }
}

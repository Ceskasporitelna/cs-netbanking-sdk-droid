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
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.cscore.webapi.signing.FilledSigningObject;
import cz.csas.cscore.webapi.signing.MobileCaseSigningProcess;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.cscore.webapi.signing.SigningState;
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
public class CaseMobileCardLimitsUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "signing.caseMobile.card.limits.update";
    private CountDownLatch mRequestSignal;
    private CountDownLatch mSignSignalGET;
    private CountDownLatch mSignSignalPOST;
    private CountDownLatch mSignSignalPUT;
    private ChangeCardLimitsResponse mResponse;
    private FilledSigningObject mFilledSigningObject;
    private MobileCaseSigningProcess mMobileCaseSigningProcess;

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
        String cardId = "33A813886442D946122C78305EC4E482DE9F574D";
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
        assertEquals("1445623889", signingObject.getSignId());

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
        assertEquals(AuthorizationType.MOBILE_CASE, mFilledSigningObject.getAuthorizationType());
        assertEquals(Arrays.asList(Arrays.asList(AuthorizationType.MOBILE_CASE)), mFilledSigningObject.getScenarios());
        assertEquals(SigningState.OPEN, mFilledSigningObject.getSigningState());
        assertEquals("1445623889", mFilledSigningObject.getSignId());


        // POST signing request

        mFilledSigningObject.startSigningWithMobileCase(new CallbackWebApi<MobileCaseSigningProcess>() {
            @Override
            public void success(MobileCaseSigningProcess mobileCaseSigningProcess) {
                CaseMobileCardLimitsUpdateTest.this.mMobileCaseSigningProcess = mobileCaseSigningProcess;
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

        assertNotNull(mMobileCaseSigningProcess);


        // Finish signing

        mMobileCaseSigningProcess.finishSigning("000000", new CallbackWebApi<SigningObject>() {
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
        assertEquals("1445623889", mFilledSigningObject.getSignId());
        assertEquals(SigningState.DONE, mFilledSigningObject.getSigningState());
        assertEquals(SigningState.DONE, mResponse.signing().getSigningState());
    }
}

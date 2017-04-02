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
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.TransferResponse;
import cz.csas.netbanking.cards.CardTransferRequest;
import cz.csas.netbanking.cards.CardTransferType;
import cz.csas.netbanking.cards.Sender;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 24.05.16.
 */
public class TacCardsTransferUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "signing.tac.cards.transfer.update";
    private CountDownLatch mRequestSignal;
    private CountDownLatch mSignSignalGET;
    private CountDownLatch mSignSignalPOST;
    private CountDownLatch mSignSignalPUT;
    private TransferResponse mResponse;
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
        String cardId = "3FB37388FC58076DEAD3DE282E075592A299B596";
        CardTransferRequest request = new CardTransferRequest(CardTransferType.DEBT_REPAYMENT,
                new Sender(null, new AccountNumber("2326573123", "0800")),
                new Amount(500000L, 2, "CZK"));
        mNetbankingClient.getCardsResource().withId(cardId).getTransferResource().update(request, new CallbackWebApi<TransferResponse>() {
            @Override
            public void success(TransferResponse transferResponse) {
                mResponse = transferResponse;
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
        assertEquals("160429968745812", signingObject.getSignId());


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
        assertEquals("160429968745812", mFilledSigningObject.getSignId());


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
        assertEquals("160429968745812", mFilledSigningObject.getSignId());
        assertEquals(SigningState.DONE, mFilledSigningObject.getSigningState());
        assertEquals(SigningState.DONE, mResponse.signing().getSigningState());
    }
}

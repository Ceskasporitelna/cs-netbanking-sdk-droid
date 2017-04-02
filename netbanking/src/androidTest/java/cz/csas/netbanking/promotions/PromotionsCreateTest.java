package cz.csas.netbanking.promotions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class PromotionsCreateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "promotions.create";
    private CountDownLatch mCountDownLatch;
    private PromotionActionCreateResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testCreatePromotion() {
        PromotionActionCreateRequest request = new PromotionActionCreateRequest("218", new ExecutedAction("HIDE", ActionType.HIDE));
        mNetbankingClient.getPromotionsResource().getActionsResource().create(request, new CallbackWebApi<PromotionActionCreateResponse>() {
            @Override
            public void success(PromotionActionCreateResponse createPromotionResponse) {
                mResponse = createPromotionResponse;
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(1, mResponse.getInfoItems().size());
        InfoItem item = mResponse.getInfoItems().get(0);
        assertEquals("RETURN_MESSAGE", item.getInfoName());
        assertEquals("successfully executed", item.getInfoValue());
    }
}

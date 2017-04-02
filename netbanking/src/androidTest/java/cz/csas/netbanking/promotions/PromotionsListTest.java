package cz.csas.netbanking.promotions;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
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
public class PromotionsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "promotions.list";
    private CountDownLatch mCountDownLatch;
    private PromotionsListResponse mResponse;

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
    public void testPromotionsList() {
        mNetbankingClient.getPromotionsResource().list(new CallbackWebApi<PromotionsListResponse>() {
            @Override
            public void success(PromotionsListResponse promotionsListResponse) {
                mResponse = promotionsListResponse;
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

        List<Promotion> promotions = mResponse.getItems();
        assertEquals(1, promotions.size());
        Promotion promotion = promotions.get(0);

        assertEquals("218", promotion.getPromotionId());

        DisplayType displayType = promotion.getDisplayType();
        assertEquals("Plugin Mobilní Platby", displayType.getTitleText());
        assertEquals("Aktivace pluginu zdarma", displayType.getSublineText());
        assertEquals(DisplayTypeKind.OVERVIEW_CARD, displayType.getDisplayType());
        assertEquals(CardDesign.PLUGIN_PROMOTION, displayType.getCardDesign());
        assertEquals("banner_ie_680x180_3020.xml", displayType.getBackgroundImage());
        assertEquals("banner_ie_222x137_26.xml", displayType.getMainImage());
        assertEquals(Integer.valueOf(1), displayType.getPosition());
        assertEquals(Integer.valueOf(4), displayType.getColumn());
        assertEquals("Aktivovat", displayType.getBtnText());
        assertEquals(ButtonDesign.PRIMARY, displayType.getBtnDesign());

        List<Action> actions = promotion.getActions();
        assertEquals(2, actions.size());

        Action action = actions.get(0);
        assertEquals("SHOWURL", action.getActionId());
        assertEquals(ActionType.SHOWURL, action.getActionType());
        assertEquals("_self", action.getTarget());
        assertEquals("#shop/plugins/PI-MOBILEPAYMENTS", action.getUrl());
        assertEquals(ProductCode.PI_MOBILEPAYMENTS, action.getProductCode());

        action = actions.get(1);
        assertEquals("HIDE", action.getActionId());
        assertEquals(ActionType.HIDE, action.getActionType());
    }

}

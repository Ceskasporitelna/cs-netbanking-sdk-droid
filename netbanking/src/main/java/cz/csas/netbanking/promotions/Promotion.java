package cz.csas.netbanking.promotions;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Promotion.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class Promotion extends WebApiEntity {

    @CsExpose
    private String promotionId;

    @CsExpose
    private DisplayType displayType;

    @CsExpose
    private List<Action> actions;

    /**
     * Id of campaign
     *
     * @return the promotion id
     */
    public String getPromotionId() {
        return promotionId;
    }

    /**
     * Promotion display type
     *
     * @return the display type
     */
    public DisplayType getDisplayType() {
        return displayType;
    }

    /**
     * Possible actions. Each action is represented by related button on the promotion card/message
     * etc.
     *
     * @return the actions
     */
    public List<Action> getActions() {
        return actions;
    }
}

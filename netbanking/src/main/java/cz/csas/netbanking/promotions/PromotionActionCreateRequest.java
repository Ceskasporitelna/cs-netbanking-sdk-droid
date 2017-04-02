package cz.csas.netbanking.promotions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Promotion create request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class PromotionActionCreateRequest extends WebApiRequest {

    @CsExpose
    private String promotionId;

    @CsExpose
    private ExecutedAction executedAction;

    public PromotionActionCreateRequest(String promotionId, ExecutedAction executedAction) {
        this.promotionId = promotionId;
        this.executedAction = executedAction;
    }

    /**
     * Id of campaign
     *
     * @return the promotion id
     */
    public String getPromotionId() {
        return promotionId;
    }

    /**
     * Id of campaign
     *
     * @param promotionId the promotion id
     */
    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public ExecutedAction getExecutedAction() {
        return executedAction;
    }

    public void setExecutedAction(ExecutedAction executedAction) {
        this.executedAction = executedAction;
    }
}

package cz.csas.netbanking.promotions;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Executed action.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 20.09.16.
 */
public class ExecutedAction {

    @CsExpose
    private String actionId;

    private ActionType actionType;

    @CsExpose
    @CsSerializedName("actionType")
    private String actionTypeRaw;

    /**
     * Instantiates a new Executed action.
     *
     * @param actionId   the action id
     * @param actionType the action type
     */
    public ExecutedAction(String actionId, String actionType) {
        this.actionId = actionId;
        this.actionTypeRaw = actionType;
    }

    /**
     * Instantiates a new Executed action.
     *
     * @param actionId   the action id
     * @param actionType the action type
     */
    public ExecutedAction(String actionId, ActionType actionType) {
        this.actionId = actionId;
        this.actionTypeRaw = actionType.getValue();
    }

    /**
     * Technical identifier of the action
     *
     * @return the action id
     */
    public String getActionId() {
        return actionId;
    }

    /**
     * Technical identifier of the action
     *
     * @param actionId the action id
     * @return the action id
     */
    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    /**
     * Type of the action button. Possible values are SHOPPRODUCT, SHOWURL, HIDE
     *
     * @return the action type
     */
    public ActionType getActionType() {
        if (actionType == null && actionTypeRaw != null)
            actionType = EnumUtils.translateToEnum(ActionType.class, actionTypeRaw);
        return actionType;
    }

    /**
     * Get action type raw.
     *
     * @return the action type raw
     */
    public String getActionTypeRaw() {
        return actionTypeRaw;
    }

    /**
     * Type of the action button. Possible values are SHOPPRODUCT, SHOWURL, HIDE
     *
     * @param actionType the action type
     * @return the action type
     */
    public void setActionType(ActionType actionType) {
        this.actionTypeRaw = actionType.getValue();
    }

    /**
     * Type of the action button. Possible values are SHOPPRODUCT, SHOWURL, HIDE
     *
     * @param actionType the action type
     * @return the action type
     */
    public void setActionType(String actionType) {
        this.actionTypeRaw = actionType;
    }
}

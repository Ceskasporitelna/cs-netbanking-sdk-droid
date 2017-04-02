package cz.csas.netbanking.promotions;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Action.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Action {

    @CsExpose
    private String actionId;

    private ActionType actionType;

    @CsExpose
    @CsSerializedName("actionType")
    private String actionTypeRaw;

    @CsExpose
    private String target;

    @CsExpose
    private String url;

    private ProductCode productCode;

    @CsExpose
    @CsSerializedName("productCode")
    private String productCodeRaw;

    @CsExpose
    private String element;

    /**
     * Technical identifier of the action
     *
     * @return the action id
     */
    public String getActionId() {
        return actionId;
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
     * Name of the window where the url should be opened. This element is mandatory only in case of
     * actionType = SHOWURL. Can be empty then same window
     *
     * @return the target
     */
    public String getTarget() {
        return target;
    }

    /**
     * Contains the URL of an external site to be called. This element is only mandatory if
     * actionType = SHOWURL
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Code of the product/plugin connected to the sales promotion. Possible values are Possible
     * values are: RUFO_ORDER, RUFO_INCREASE, UFO_ORDER, UFO_INCREASE.
     *
     * @return the product code
     */
    public ProductCode getProductCode() {
        if (productCode == null && productCodeRaw != null)
            productCode = EnumUtils.translateToEnum(ProductCode.class, productCodeRaw);
        return productCode;
    }

    /**
     * Get product code raw.
     *
     * @return the product code raw
     */
    public String getProductCodeRaw() {
        return productCodeRaw;
    }

    /**
     * Element connected to this action. Application specific attribute.
     *
     * @return the element
     */
    public String getElement() {
        return element;
    }
}

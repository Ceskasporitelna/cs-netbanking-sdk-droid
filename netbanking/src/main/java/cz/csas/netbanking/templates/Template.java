package cz.csas.netbanking.templates;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.accounts.FullAccountNumber;

/**
 * The type Template.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Template extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String name;

    private OrderCategory orderCategory;

    @CsExpose
    @CsSerializedName("orderCategory")
    private String orderCategoryRaw;

    @CsExpose
    private FullAccountNumber receiver;

    /**
     * Template ID
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Name defined by user
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Order category. Possible values: DOMESTIC, INTERNATIONAL
     *
     * @return the order category
     */
    public OrderCategory getOrderCategory() {
        if (orderCategory == null && orderCategoryRaw != null)
            orderCategory = EnumUtils.translateToEnum(OrderCategory.class, orderCategoryRaw);
        return orderCategory;
    }

    /**
     * Get order category raw.
     *
     * @return the order category raw
     */
    public String getOrderCategoryRaw() {
        return orderCategoryRaw;
    }

    /**
     * Receiver account number.
     *
     * @return the receiver
     */
    public FullAccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Convenience method for fetching templates detail
     *
     * @param callback
     */
    public void get(CallbackWebApi<Template> callback) {
        if (resource instanceof TemplateResource)
            ((TemplateResource) resource).get(callback);
    }
}

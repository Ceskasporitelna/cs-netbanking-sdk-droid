package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Payment limits resource.
 * This resource provides information about remaining amounts for payment orders.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentLimitsResource extends Resource implements ListEnabled<PaymentLimitsListResponse> {

    /**
     * Instantiates a new Payment limits resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentLimitsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of remaining payment limits.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<PaymentLimitsListResponse> callback) {
        ResourceUtils.callList(this, null, PaymentLimitsListResponse.class, callback);
    }
}

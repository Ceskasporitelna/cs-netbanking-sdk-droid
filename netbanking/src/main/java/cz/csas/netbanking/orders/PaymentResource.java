package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * Payment resource. This instance resource provide information about Payment Order.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentResource extends InstanceResource implements GetEnabled<Payment>,
        DeleteEnabled<WebApiEmptyResponse> {

    /**
     * Instantiates a new Payment resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get detail of the payment order.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Payment> callback) {
        ResourceUtils.callGet(this, null, null, Payment.class, callback);
    }

    /**
     * Remove payment order. It is possible to delete only payments flagged as deletable or cancelable.
     *
     * @param callback the callback
     */
    @Override
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        ResourceUtils.callDelete(this, null, null, WebApiEmptyResponse.class, callback);
    }
}

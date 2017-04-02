package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Account standing order resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class AccountStandingOrderResource extends InstanceResource implements GetEnabled<StandingOrder>, DeleteEnabled<StandingOrderResponse> {

    public AccountStandingOrderResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * This call removes existing standing/sweep order. No more payments for the order are executed
     * after the change has been signed.
     *
     * @param callback the callback
     */
    @Override
    public void delete(CallbackWebApi<StandingOrderResponse> callback) {
        ResourceUtils.callDelete(this, null, null, StandingOrderResponse.class, callback);
    }

    /**
     * Returns detail of actual standing/sweep orders identified by its number.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<StandingOrder> callback) {
        ResourceUtils.callGet(this, null, null, StandingOrder.class, callback);
    }
}

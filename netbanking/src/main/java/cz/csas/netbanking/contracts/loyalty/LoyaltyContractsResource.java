package cz.csas.netbanking.contracts.loyalty;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Loyalty resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class LoyaltyContractsResource extends Resource implements GetEnabled<Loyalty> {

    public LoyaltyContractsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Get data about iBod account of the current client.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Loyalty> callback) {
        ResourceUtils.callGet(this, null, null, Loyalty.class, callback);
    }
}

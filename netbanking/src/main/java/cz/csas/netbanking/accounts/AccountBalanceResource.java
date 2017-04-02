package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Balance resource provides Balance information and data.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountBalanceResource extends Resource implements GetEnabled<AccountBalance> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountBalanceResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get Account balance defined by this resource.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<AccountBalance> callback) {
        ResourceUtils.callGet(this, null, null, AccountBalance.class, callback);
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Accounts resource. This resource provides Accounts data and information in lists.
 * Also provides access to instance resource for account detail.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountsResource extends Resource implements HasInstanceResource<AccountResource>,
        PaginatedListEnabled<AccountsParameters, AccountsListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource of AccountResource using Account id to access account detail.
     *
     * @param id the id of Account
     * @return AccountResource
     */
    @Override
    public AccountResource withId(Object id) {
        return new AccountResource(id, getBasePath(), getClient());
    }

    /**
     * Get list of all Accounts based on given parameters
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(AccountsParameters parameters, CallbackWebApi<AccountsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, AccountsListResponse.class, callback);
    }
}

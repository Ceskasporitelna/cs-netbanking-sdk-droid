package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;
import cz.csas.netbanking.ServicesListResponse;

/**
 * The type Services resource provides list of services for account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountServicesResource extends Resource implements PaginatedListEnabled<PaginatedParameters,
        ServicesListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountServicesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Get list of services according to given parameters.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<ServicesListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, ServicesListResponse.class, callback);
    }
}

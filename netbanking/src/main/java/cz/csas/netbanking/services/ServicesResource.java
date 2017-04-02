package cz.csas.netbanking.services;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;
import cz.csas.netbanking.ServicesListResponse;

/**
 * The type Services resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class ServicesResource extends Resource implements PaginatedListEnabled<PaginatedParameters, ServicesListResponse> {

    public ServicesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns possibly empty list of services for current user. This resource represents only
     * services which are not bound to any product.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<ServicesListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, ServicesListResponse.class, callback);
    }
}

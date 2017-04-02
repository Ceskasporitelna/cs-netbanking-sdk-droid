package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;
import cz.csas.netbanking.ServicesListResponse;

/**
 * The type Buildings contracts services resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContractsServicesResource extends Resource implements PaginatedListEnabled<PaginatedParameters, ServicesListResponse> {

    public BuildingsContractsServicesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of services which are connected or arranged for building saving product instance.
     *
     * @param parameters the query parameters
     * @param callback   the callback of type CallbackWebApi<T>
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<ServicesListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, ServicesListResponse.class, callback);
    }
}
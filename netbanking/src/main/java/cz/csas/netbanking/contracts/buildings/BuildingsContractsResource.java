package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Buildings contracts resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContractsResource extends Resource implements HasInstanceResource<BuildingsContractResource>,
        PaginatedListEnabled<PaginatedParameters, BuildingsListResponse> {

    public BuildingsContractsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get the resource of buildings contract with a given id
     *
     * @param id building contract ID
     * @return
     */
    @Override
    public BuildingsContractResource withId(Object id) {
        return new BuildingsContractResource(id, getBasePath(), getClient());
    }

    /**
     * Resource represents list of building savings for current user. It contains building savings and loans from building savings as well.
     *
     * @param parameters query parameters
     * @param callback   WebApi callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<BuildingsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, BuildingsListResponse.class, callback);
    }
}

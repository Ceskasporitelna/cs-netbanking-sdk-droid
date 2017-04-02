package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Pensions contracts resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class PensionsContractsResource extends Resource implements HasInstanceResource<PensionsContractResource>,
        PaginatedListEnabled<PaginatedParameters, PensionsListResponse> {

    public PensionsContractsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Get the resource of pension contract with a given id
     *
     * @param id the id of InstanceResource
     * @return the resource
     */
    @Override
    public PensionsContractResource withId(Object id) {
        return new PensionsContractResource(id, getBasePath(), getClient());
    }

    /**
     * Returns list of pension products which belongs to current user. This includes Pension
     * Savings, Supplementary Pension Insurance and Supplementary Pension Savings.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<PensionsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, PensionsListResponse.class, callback);
    }
}

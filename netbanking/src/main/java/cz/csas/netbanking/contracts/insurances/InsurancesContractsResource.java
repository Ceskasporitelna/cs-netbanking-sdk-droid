package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Insurances contracts resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractsResource extends Resource implements HasInstanceResource<InsurancesContractResource>,
        PaginatedListEnabled<PaginatedParameters, InsuranceListResponse> {

    public InsurancesContractsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get the resource of insurance contracts with a given id
     *
     * @param id the id
     * @return the resource
     */
    @Override
    public InsurancesContractResource withId(Object id) {
        return new InsurancesContractResource(id, getBasePath(), getClient());
    }

    /**
     * Returns list of life insurances for current user.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<InsuranceListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, InsuranceListResponse.class, callback);
    }
}

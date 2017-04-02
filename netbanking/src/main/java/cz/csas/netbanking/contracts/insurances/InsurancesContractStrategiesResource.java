package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract strategies resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractStrategiesResource extends Resource implements ListEnabled<ContractStrategiesListResponse> {

    public InsurancesContractStrategiesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of strategies with corresponsing funds allocation for the life insurance
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<ContractStrategiesListResponse> callback) {
        ResourceUtils.callList(this, null, ContractStrategiesListResponse.class, callback);
    }
}

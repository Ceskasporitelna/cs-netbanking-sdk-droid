package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract payments resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractPaymentsResource extends Resource implements ListEnabled<ContractPaymentsListResponse> {

    public InsurancesContractPaymentsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of life insurance payments. List contains one upcoming payment and payments history for 2 years.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<ContractPaymentsListResponse> callback) {
        ResourceUtils.callList(this, null, ContractPaymentsListResponse.class, callback);
    }
}

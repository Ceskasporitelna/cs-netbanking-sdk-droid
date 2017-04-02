package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract events resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractEventsResource extends Resource implements ListEnabled<ContractEventsListResponse> {

    public InsurancesContractEventsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of events for the life insurance
     *
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void list(CallbackWebApi<ContractEventsListResponse> callback) {
        ResourceUtils.callList(this, null, ContractEventsListResponse.class, callback);
    }
}

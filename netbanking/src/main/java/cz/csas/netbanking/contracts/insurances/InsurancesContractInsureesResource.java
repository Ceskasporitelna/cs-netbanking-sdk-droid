package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract insurees resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractInsureesResource extends Resource implements ListEnabled<InsureesListResponse> {

    public InsurancesContractInsureesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of insurees related to the insurance contract.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<InsureesListResponse> callback) {
        ResourceUtils.callList(this, null, InsureesListResponse.class, callback);
    }
}

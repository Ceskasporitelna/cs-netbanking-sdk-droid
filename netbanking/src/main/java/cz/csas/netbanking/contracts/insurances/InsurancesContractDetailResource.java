package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Insurances contract detail resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractDetailResource extends Resource implements GetEnabled<InsuranceDetail> {

    public InsurancesContractDetailResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Get contract detail info
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<InsuranceDetail> callback) {
        ResourceUtils.callGet(this, null, null, InsuranceDetail.class, callback);
    }
}

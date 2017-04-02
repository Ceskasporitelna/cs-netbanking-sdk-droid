package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Insurances contract transfer resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancesContractTransferResource extends Resource implements UpdateEnabled<InsuranceTransferUpdateRequest, InsuranceTransferUpdateResponse> {

    public InsurancesContractTransferResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Creates insurance transfer - premium payment, extra deposit or recommended deposit.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(InsuranceTransferUpdateRequest request, CallbackWebApi<InsuranceTransferUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, InsuranceTransferUpdateResponse.class, callback);
    }
}

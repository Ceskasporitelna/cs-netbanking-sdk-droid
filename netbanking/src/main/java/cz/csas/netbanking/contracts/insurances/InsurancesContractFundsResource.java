package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Insurances contract funds resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractFundsResource extends Resource implements ListEnabled<FundsListResponse>, UpdateEnabled<FundsUpdateRequest, FundsUpdateResponse> {

    public InsurancesContractFundsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns detail of distribution of capital value into funds.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<FundsListResponse> callback) {
        ResourceUtils.callList(this, null, FundsListResponse.class, callback);
    }

    /**
     * Change the distribution of capital value into funds.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(FundsUpdateRequest request, CallbackWebApi<FundsUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, FundsUpdateResponse.class, callback);
    }
}

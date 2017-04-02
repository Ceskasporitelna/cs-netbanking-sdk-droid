package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Insurances contract beneficiaries resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractBeneficiariesResource extends Resource implements ListEnabled<InsuranceBeneficiariesListResponse>, UpdateEnabled<InsuranceBeneficiariesUpdateRequest, InsuranceBeneficiariesUpdateResponse> {

    public InsurancesContractBeneficiariesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of beneficiaries related to the insurance contract.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<InsuranceBeneficiariesListResponse> callback) {
        ResourceUtils.callList(this, null, InsuranceBeneficiariesListResponse.class, callback);
    }

    /**
     * Change beneficiaries and distribution of insurance among beneficiaries.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(InsuranceBeneficiariesUpdateRequest request, CallbackWebApi<InsuranceBeneficiariesUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, InsuranceBeneficiariesUpdateResponse.class, callback);
    }
}

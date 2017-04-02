package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract services resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractServicesResource extends Resource implements ListEnabled<InsuranceServicesListResponse> {

    public InsurancesContractServicesResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of services for the life insurance
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<InsuranceServicesListResponse> callback) {
        ResourceUtils.callList(this, null, InsuranceServicesListResponse.class, callback);
    }

    public void activateRiskSports(RiskSportsUpdateRequest request, CallbackWebApi<RiskSportsActivationUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, "riskSportsActivation", request, null, RiskSportsActivationUpdateResponse.class, callback);
    }

    public void deactivateRiskSports(RiskSportsUpdateRequest request, CallbackWebApi<RiskSportsDeactivationUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, "riskSportsDeactivation", request, null, RiskSportsDeactivationUpdateResponse.class, callback);
    }
}

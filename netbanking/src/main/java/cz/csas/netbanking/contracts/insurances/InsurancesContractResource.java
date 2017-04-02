package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Insurances contract resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractResource extends InstanceResource implements GetEnabled<Insurance>, UpdateEnabled<InsuranceUpdateRequest, InsuranceUpdateResponse> {

    // TODO check get and detail get. difference? unify one get

    public InsurancesContractResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Returns detail of the life insurance
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Insurance> callback) {
        ResourceUtils.callGet(this, null, null, Insurance.class, callback);
    }

    /**
     * Allows to change a limited set of insurance settings of one specific contract. Currently
     * only the field alias can be changed. Change only to alias field must not be signed, but
     * response is ready also for signing process.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(InsuranceUpdateRequest request, CallbackWebApi<InsuranceUpdateResponse> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, InsuranceUpdateResponse.class, callback);
    }

    /**
     * Returns detail resource of the insurances contract
     *
     * @return the detail resource
     */
    public InsurancesContractDetailResource getDetailResource() {
        return new InsurancesContractDetailResource(appendPathWith("detail"), getClient());
    }

    /**
     * Returns funds resource for insurance contract
     *
     * @return the funds resource
     */
    public InsurancesContractFundsResource getFundsResource() {
        return new InsurancesContractFundsResource(appendPathWith("funds"), getClient());
    }

    /**
     * Returns beneficiaries resource for insurance contract
     *
     * @return the beneficiaries resource
     */
    public InsurancesContractBeneficiariesResource getBeneficiariesResource() {
        return new InsurancesContractBeneficiariesResource(appendPathWith("beneficiaries"), getClient());
    }

    /**
     * Returns insurees resource for insurance contract
     *
     * @return the insurees resource
     */
    public InsurancesContractInsureesResource getInsureesResource() {
        return new InsurancesContractInsureesResource(appendPathWith("insurees"), getClient());
    }

    /**
     * Returns payments resource for insurance contract
     *
     * @return the payments resource
     */
    public InsurancesContractPaymentsResource getPaymentsResource() {
        return new InsurancesContractPaymentsResource(appendPathWith("payments"), getClient());
    }

    /**
     * Returns services resource for insurance contract
     *
     * @return the services resource
     */
    public InsurancesContractServicesResource getServicesResource() {
        return new InsurancesContractServicesResource(appendPathWith("services"), getClient());
    }

    /**
     * Returns events resource for insurance contract
     *
     * @return the events resource
     */
    public InsurancesContractEventsResource getEventsResource() {
        return new InsurancesContractEventsResource(appendPathWith("events"), getClient());
    }

    /**
     * Returns taxBenefits resource for insurance contract
     *
     * @return the tax benefits resource
     */
    public InsurancesContractTaxBenefitsResource getTaxBenefitsResource() {
        return new InsurancesContractTaxBenefitsResource(appendPathWith("taxBenefits"), getClient());
    }

    /**
     * Returns strategies resource for insurance contract
     *
     * @return the strategies resource
     */
    public InsurancesContractStrategiesResource getStrategiesResource() {
        return new InsurancesContractStrategiesResource(appendPathWith("strategies"), getClient());
    }

    /**
     * Returns transfer resource for insurance contract
     *
     * @return the transfer resource
     */
    public InsurancesContractTransferResource getTransferResource() {
        return new InsurancesContractTransferResource(appendPathWith("transfer"), getClient());
    }

}

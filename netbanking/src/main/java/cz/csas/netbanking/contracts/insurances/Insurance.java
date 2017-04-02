package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Insurance.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Insurance extends WebApiEntity {

    @CsExpose
    private String id;

    private InsuranceType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String product;

    @CsExpose
    private String productI18N;

    @CsExpose
    private String alias;

    @CsExpose
    private String insurancePolicyHolder;

    @CsExpose
    private String policyNumber;

    private InsuranceStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private Life life;

    /**
     * Contract number.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Product Type of insurance. ENUM values: LIFE (CSAS supports only this value)
     *
     * @return the type
     */
    public InsuranceType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(InsuranceType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Code of the sVersicherung product.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Name of the sVersicherung product (localised).
     *
     * @return the product i 18 n
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * User-specific alias of the contract. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * The primary holder of the specific insurance contract.
     *
     * @return the insurance policy holder
     */
    public String getInsurancePolicyHolder() {
        return insurancePolicyHolder;
    }

    /**
     * Policy number
     *
     * @return the policy number
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Status: ACTIVE or CLOSED
     *
     * @return the status
     */
    public InsuranceStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(InsuranceStatus.class, statusRaw);
        return status;
    }

    /**
     * Get status raw.
     *
     * @return the status raw
     */
    public String getStatusRaw() {
        return statusRaw;
    }

    /**
     * Get life insurance info
     *
     * @return the life
     */
    public Life getLife() {
        return life;
    }

    /**
     * Convenience get method for fetching Insurance detail
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Insurance> callback) {
        if (resource instanceof InsurancesContractResource)
            ((InsurancesContractResource) resource).get(callback);
    }

    /**
     * Convenience method for updating insurance
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(InsuranceUpdateRequest request, CallbackWebApi<InsuranceUpdateResponse> callback) {
        if (resource instanceof InsurancesContractResource)
            ((InsurancesContractResource) resource).update(request, callback);
    }

    /**
     * Convenience getter for Insurance details
     *
     * @return the detail resource
     */
    public InsurancesContractDetailResource getDetailResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getDetailResource();
        return null;
    }

    /**
     * Convenience getter for Insurance funds
     *
     * @return the funds resource
     */
    public InsurancesContractFundsResource getFundsResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getFundsResource();
        return null;
    }

    /**
     * Convenience getter for Insurance beneficiaries
     *
     * @return the beneficiaries resource
     */
    public InsurancesContractBeneficiariesResource getBeneficiariesResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getBeneficiariesResource();
        return null;
    }

    /**
     * Convenience getter for Insurance insurees
     *
     * @return the insurees resource
     */
    public InsurancesContractInsureesResource getInsureesResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getInsureesResource();
        return null;
    }

    /**
     * Convenience getter for Insurance payments
     *
     * @return the payments resource
     */
    public InsurancesContractPaymentsResource getPaymentsResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getPaymentsResource();
        return null;
    }

    /**
     * Convenience getter for Insurance services
     *
     * @return the services resource
     */
    public InsurancesContractServicesResource getServicesResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getServicesResource();
        return null;
    }

    /**
     * Convenience getter for Insurance events
     *
     * @return the events resource
     */
    public InsurancesContractEventsResource getEventsResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getEventsResource();
        return null;
    }

    /**
     * Convenience getter for Insurance tax benefits
     *
     * @return the tax benefits resource
     */
    public InsurancesContractTaxBenefitsResource getTaxBenefitsResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getTaxBenefitsResource();
        return null;
    }

    /**
     * Convenience getter for Insurance strategies
     *
     * @return the strategies resource
     */
    public InsurancesContractStrategiesResource getStrategiesResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getStrategiesResource();
        return null;
    }

    /**
     * Convenience getter for Insurance strategies
     *
     * @return the transfer resource
     */
    public InsurancesContractTransferResource getTransferResource() {
        if (resource instanceof InsurancesContractResource)
            return ((InsurancesContractResource) resource).getTransferResource();
        return null;
    }
}

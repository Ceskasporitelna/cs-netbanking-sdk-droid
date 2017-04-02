package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Beneficiaries update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceBeneficiariesUpdateRequest extends WebApiRequest {

    @CsExpose
    private List<InsuranceBeneficiary> beneficiaries;

    /**
     * Instantiates a new Insurance beneficiaries update request.
     *
     * @param beneficiaries the beneficiaries
     */
    public InsuranceBeneficiariesUpdateRequest(List<InsuranceBeneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }

    /**
     * Get beneficiaries.
     *
     * @return the beneficiaries
     */
    public List<InsuranceBeneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    /**
     * Set beneficiaries.
     *
     * @param beneficiaries the beneficiaries
     */
    public void setBeneficiaries(List<InsuranceBeneficiary> beneficiaries) {
        this.beneficiaries = beneficiaries;
    }
}

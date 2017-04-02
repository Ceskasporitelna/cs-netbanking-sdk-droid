package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Beneficiaries list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceBeneficiariesListResponse extends ListResponse<InsuranceBeneficiary> {

    @CsExpose
    @CsSerializedName(value = "beneficiaries", alternate = "items")
    private List<InsuranceBeneficiary> beneficiaries;

    @CsExpose
    private List<String> flags;

    @Override
    protected List<InsuranceBeneficiary> getItems() {
        return beneficiaries;
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
     * Get flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

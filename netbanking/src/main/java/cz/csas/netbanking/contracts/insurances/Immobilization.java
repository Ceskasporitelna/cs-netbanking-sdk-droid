package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Immobilization.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Immobilization {

    @CsExpose
    private String contractNumber;

    @CsExpose
    private String partner;

    /**
     * Immobilization secures the loan agreement with this contract number
     *
     * @return the contract number
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Immobilization partner - third party name
     *
     * @return the partner
     */
    public String getPartner() {
        return partner;
    }
}

package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Risk.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Risk {

    @CsExpose
    private String productName;

    @CsExpose
    private String riskGroup;

    @CsExpose
    private Amount insuredSum;

    @CsExpose
    private String frequency;

    @CsExpose
    private String explanation;

    /**
     * Name of the active risk product.
     *
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Group of the active risk product.
     *
     * @return the risk group
     */
    public String getRiskGroup() {
        return riskGroup;
    }

    /**
     * Amount that an ​insurance ​company will ​pay after making a ​claim.
     *
     * @return the insured sum
     */
    public Amount getInsuredSum() {
        return insuredSum;
    }

    /**
     * Frequency in which insured sum may be paid.
     *
     * @return the frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * Description of the insured risk.
     *
     * @return the explanation
     */
    public String getExplanation() {
        return explanation;
    }
}

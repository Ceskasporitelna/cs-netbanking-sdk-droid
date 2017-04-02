package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Sub account provides information about sub account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public class SubAccount extends Account {

    @CsExpose
    @CsSerializedName(value = "cz-interestRateOverLimit")
    private Double czInterestRateOverLimit;

    @CsExpose
    @CsSerializedName(value = "cz-interestRateLimit")
    private Amount czInterestRateLimit;

    /**
     * Get interest rate which applies to value over limit.
     *
     * @return the interest rate over limit
     */
    public Double getCzInterestRateOverLimit() {
        return czInterestRateOverLimit;
    }

    /**
     * Get limit amount for basic credit interest rate used for some saving accounts.
     *
     * @return the interest rate limit
     */
    public Amount getCzInterestRateLimit() {
        return czInterestRateLimit;
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Installment frequency.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public enum InstallmentFrequency implements HasValue {

    /**
     * Monthly installment frequency.
     */
    MONTHLY("MONTHLY"),

    /**
     * Quarterly installment frequency.
     */
    QUARTERLY("QUARTERLY"),

    /**
     * Halfyearly installment frequency.
     */
    HALFYEARLY("HALFYEARLY"),

    /**
     * Yearly installment frequency.
     */
    YEARLY("YEARLY"),

    /**
     * Weekly installment frequency.
     */
    WEEKLY("WEEKLY"),

    /**
     * Other installment frequency.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Installment frequency.
     *
     * @param value the value
     */
    InstallmentFrequency(String value) {
        this.value = value;
    }

    /**
     * Get value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

}

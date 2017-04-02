package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Payment Interval. ENUM: ONCE, MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, UNKNOWN
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum Interval implements HasValue {

    /**
     * Once interval.
     */
    ONCE("ONCE"),

    /**
     * Monthly interval.
     */
    MONTHLY("MONTHLY"),

    /**
     * Quarterly interval.
     */
    QUARTERLY("QUARTERLY"),

    /**
     * Halfyearly interval.
     */
    HALFYEARLY("HALFYEARLY"),

    /**
     * Yearly interval.
     */
    YEARLY("YEARLY"),

    /**
     * Unknown interval.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other interval.
     */
    OTHER(null);

    private String value;

    Interval(String value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return
     */
    @Override
    public String getValue() {
        return value;
    }
}

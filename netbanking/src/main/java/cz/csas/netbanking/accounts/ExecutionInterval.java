package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Execution interval defines how often order is executed. Possible values: DAILY, WEEKLY, MONTHLY,
 * BI_MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, IRREGULAR.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public enum ExecutionInterval implements HasValue {

    /**
     * Daily execution interval.
     */
    DAILY("DAILY"),

    /**
     * Weekly execution interval.
     */
    WEEKLY("WEEKLY"),

    /**
     * Monthly execution interval.
     */
    MONTHLY("MONTHLY"),

    /**
     * Bi monthly execution interval.
     */
    BI_MONTHLY("BI_MONTHLY"),

    /**
     * Quarterly execution interval.
     */
    QUARTERLY("QUARTERLY"),

    /**
     * Half-yearly execution interval.
     */
    HALFYEARLY("HALFYEARLY"),

    /**
     * Yearly execution interval.
     */
    YEARLY("YEARLY"),

    /**
     * Irregular execution interval.
     */
    IRREGULAR("IRREGULAR"),
    /**
     * Other execution interval.
     */
    OTHER(null);

    private String value;

    ExecutionInterval(String value) {
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

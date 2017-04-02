package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Unit of the period cycle. Possible values are HALFYEARLY, MONTHLY, QUARTERLY, YEARLY, DAILY,
 * WEEKLY, OTHER.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum PeriodCycle implements HasValue {

    /**
     * Daily period cycle.
     */
    DAILY("DAILY"),

    /**
     * Weekly period cycle.
     */
    WEEKLY("WEEKLY"),

    /**
     * Monthly period cycle.
     */
    MONTHLY("MONTHLY"),

    /**
     * Quarterly period cycle.
     */
    QUARTERLY("QUARTERLY"),

    /**
     * Halfyearly period cycle.
     */
    HALFYEARLY("HALFYEARLY"),

    /**
     * Yearly period cycle.
     */
    YEARLY("YEARLY"),

    /**
     * Other period cycle.
     */
    OTHER(null);

    private String value;

    PeriodCycle(String value) {
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

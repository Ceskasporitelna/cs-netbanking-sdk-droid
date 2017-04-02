package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The execution due mode defines how the date when order should be executed is specified. Possible
 * values: DUE_DAY_OF_MONTH (specific number of day in the month is defined) or
 * DUE_LAST_DAY_OF_MONTH (order is executed on last day of particular month).
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum ExecutionDueMode implements HasValue {

    /**
     * Specific number of day in the month is defined
     */
    DUE_DAY_OF_MONTH("DUE_DAY_OF_MONTH"),

    /**
     * Order is executed on last day of particular month
     */
    DUE_LAST_DAY_OF_MONTH("DUE_LAST_DAY_OF_MONTH"),

    /**
     * Other execution due mode.
     */
    OTHER(null);

    private String value;

    ExecutionDueMode(String value) {
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

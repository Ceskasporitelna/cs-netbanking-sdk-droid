package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the chosen strategy. Possible values: CONSERVATIVE, PROGRESSIVE, BALANCED, CONTROL,
 * ACTUAL_SETTING
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum ContractStrategyType implements HasValue {

    /**
     * Conservative contract strategy type.
     */
    CONSERVATIVE("CONSERVATIVE"),

    /**
     * Progressive contract strategy type.
     */
    PROGRESSIVE("PROGRESSIVE"),

    /**
     * Balanced contract strategy type.
     */
    BALANCED("BALANCED"),

    /**
     * Control contract strategy type.
     */
    CONTROL("CONTROL"),

    /**
     * Actual setting contract strategy type.
     */
    ACTUAL_SETTING("ACTUAL_SETTING"),

    /**
     * Other contract strategy type.
     */
    OTHER(null);

    private String value;

    ContractStrategyType(String value) {
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

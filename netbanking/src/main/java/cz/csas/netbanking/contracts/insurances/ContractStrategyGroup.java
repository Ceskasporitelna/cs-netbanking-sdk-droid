package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Possible values are STRATEGY, INVESTMENT_MANAGEMENT. That means the funds allocation is fixed
 * given by the chosen strategy, or it is under an investment program, so it is variable depending
 * on current market state.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum ContractStrategyGroup implements HasValue {

    /**
     * Chosen strategy group.
     */
    STRATEGY("STRATEGY"),

    /**
     * Investment management contract strategy group.
     */
    INVESTMENT_MANAGEMENT("INVESTMENT_MANAGEMENT"),

    /**
     * Other contract strategy group.
     */
    OTHER(null);

    private String value;

    ContractStrategyGroup(String value) {
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

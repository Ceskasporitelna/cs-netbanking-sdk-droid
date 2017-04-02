package cz.csas.netbanking.contracts.loyalty;

import cz.csas.cscore.webapi.HasValue;

/**
 * State of the ibod account. Possible values are REGISTERED, UNREGISTERED, DEACTIVATED_FROM_FSCS.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum LoyaltyState implements HasValue {

    /**
     * Registered loyalty state.
     */
    REGISTERED("REGISTERED"),

    /**
     * Unregistered loyalty state.
     */
    UNREGISTERED("UNREGISTERED"),

    /**
     * Deactivated from fscs loyalty state.
     */
    DEACTIVATED_FROM_FSCS("DEACTIVATED_FROM_FSCS"),

    /**
     * Other loyalty state.
     */
    OTHER(null);

    private String value;

    LoyaltyState(String value) {
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

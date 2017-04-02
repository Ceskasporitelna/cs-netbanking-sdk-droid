package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum State.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 *
 * Mapping for stateDetail is available in {@link PaymentStateDetail}
 */
public enum PaymentState implements HasValue {

    /**
     * Open state.
     */
    OPEN("OPEN"),

    /**
     * Spooled state.
     */
    SPOOLED("SPOOLED"),

    /**
     * Cancelled state.
     */
    CANCELLED("CANCELLED"),

    /**
     * Closed state.
     */
    CLOSED("CLOSED"),

    /**
     * Deleted state.
     */
    DELETED("DELETED"),

    /**
     * Other state.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new State.
     *
     * @param value the value
     */
    PaymentState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

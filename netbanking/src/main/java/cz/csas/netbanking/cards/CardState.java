package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card state.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardState implements HasValue {

    /**
     * Active card state.
     */
    ACTIVE("ACTIVE"),

    /**
     * Inactive card state.
     */
    INACTIVE("INACTIVE"),

    /**
     * Temporary blocked card state.
     */
    TEMPORARY_BLOCKED("TEMPORARY_BLOCKED"),

    /**
     * Other card state.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card state.
     *
     * @param value the value
     */
    CardState(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

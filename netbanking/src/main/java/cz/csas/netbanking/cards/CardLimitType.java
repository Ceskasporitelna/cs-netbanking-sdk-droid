package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card limit type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardLimitType implements HasValue {

    /**
     * Atm card limit type.
     */
    ATM("ATM"),

    /**
     * Pos card limit type.
     */
    POS("POS"),

    /**
     * Internet card limit type.
     */
    INTERNET("INTERNET"),

    /**
     * Other card limit type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card limit type.
     *
     * @param value the value
     */
    CardLimitType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

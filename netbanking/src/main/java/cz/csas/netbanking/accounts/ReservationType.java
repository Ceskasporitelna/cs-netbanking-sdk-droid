package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Reservation type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum ReservationType implements HasValue {

    /**
     * Cash withdrawal reservation type.
     */
    CASH_WITHDRAWAL("CASH_WITHDRAWAL"),

    /**
     * Payment reservation type.
     */
    PAYMENT("PAYMENT"),

    /**
     * Card payment reservation type.
     */
    CARD_PAYMENT("CARD_PAYMENT"),

    /**
     * Other reservation type.
     */
    OTHER("OTHER");

    private String value;

    /**
     * Instantiates a new Reservation type.
     *
     * @param value the value
     */
    ReservationType(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}

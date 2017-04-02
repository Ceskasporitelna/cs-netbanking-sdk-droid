package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Reservation status.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum ReservationStatus implements HasValue {

    /**
     * Reserved reservation status.
     */
    RESERVED("RESERVED"),

    /**
     * Cancelled reservation status.
     */
    CANCELLED("CANCELLED"),

    /**
     * Expired reservation status.
     */
    EXPIRED("EXPIRED"),

    /**
     * Other reservation status.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Reservation status.
     *
     * @param value the value
     */
    ReservationStatus(String value) {
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

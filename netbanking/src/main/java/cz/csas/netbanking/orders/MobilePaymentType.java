package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Payment type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum MobilePaymentType implements HasValue {

    /**
     * Top up payment type.
     */
    TOP_UP("TOP_UP"),

    /**
     * Invoice payment type.
     */
    INVOICE("INVOICE"),

    /**
     * Vodafone payment payment type.
     */
    VODAFONE_PAYMENT("VODAFONE_PAYMENT"),

    /**
     * Mobile deposit payment type.
     */
    MOBILE_DEPOSIT("MOBILE_DEPOSIT"),

    /**
     * Unknown payment type.
     */
    UNKNOWN("unknown"),

    /**
     * Other payment type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Payment type.
     *
     * @param value the value
     */
    MobilePaymentType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Payment order priority.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum PaymentOrderPriority implements HasValue {

    /**
     * Urgent payment order priority.
     */
    URGENT("URGENT"),

    /**
     * Standard payment order priority.
     */
    STANDARD("STANDARD"),

    /**
     * Other payment order priority.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Payment order priority.
     *
     * @param value the value
     */
    PaymentOrderPriority(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

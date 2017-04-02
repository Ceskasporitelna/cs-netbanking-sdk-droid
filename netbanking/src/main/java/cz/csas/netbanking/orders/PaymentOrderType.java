package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Payment type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum PaymentOrderType implements HasValue {

    /**
     * Payment out order type.
     */
    PAYMENT_OUT("PAYMENT_OUT"),

    /**
     * Direct debit in order type.
     */
    DIRECT_DEBIT_IN("DIRECT_DEBIT_IN"),

    /**
     * Other order type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Payment type.
     *
     * @param value the value
     */
    PaymentOrderType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

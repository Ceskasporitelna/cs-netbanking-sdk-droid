package cz.csas.netbanking.orders;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 16.05.16.
 */

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Sortable fields.
 */
public enum PaymentsSortableFields implements HasValue {

    /**
     * Statement date
     */
    TRANSFER_DATE("transferDate"),

    /**
     * Other sort parameters.
     */
    OTHER(null);

    private String value;

    PaymentsSortableFields(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Other user sort parameters.
     *
     * @param value the value
     * @return the user sort parameters
     */
    public static PaymentsSortableFields other(String value) {
        PaymentsSortableFields parameters = PaymentsSortableFields.OTHER;
        parameters.setValue(value);
        return parameters;
    }
}

package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of payment template. Possible values - ORDINARY, EXTRAORDINARY
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum PaymentTemplateType implements HasValue {

    /**
     * Ordinary payment template type.
     */
    ORDINARY("ORDINARY"),

    /**
     * Extraordinary payment template type.
     */
    EXTRAORDINARY("EXTRAORDINARY"),

    /**
     * Other payment template type.
     */
    OTHER(null);

    private String value;

    PaymentTemplateType(String value) {
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

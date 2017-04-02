package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the payment. Possible values are ORDINARY, ONETIME, EXTRAORDINARY, FUTURE, OVERDUE,
 * WITHDRAWAL, PARTIALLY_PAID, UNKNOWN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum PaymentType implements HasValue {

    /**
     * Ordinary payment type.
     */
    ORDINARY("ORDINARY"),

    /**
     * Onetime payment type.
     */
    ONETIME("ONETIME"),

    /**
     * Extraordinary payment type.
     */
    EXTRAORDINARY("EXTRAORDINARY"),

    /**
     * Future payment type.
     */
    FUTURE("FUTURE"),

    /**
     * Overdue payment type.
     */
    OVERDUE("OVERDUE"),

    /**
     * Withdrawal payment type.
     */
    WITHDRAWAL("WITHDRAWAL"),

    /**
     * Partially paid payment type.
     */
    PARTIALLY_PAID("PARTIALLY_PAID"),

    /**
     * Unknown payment type.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other payment type.
     */
    OTHER(null);

    private String value;

    PaymentType(String value) {
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

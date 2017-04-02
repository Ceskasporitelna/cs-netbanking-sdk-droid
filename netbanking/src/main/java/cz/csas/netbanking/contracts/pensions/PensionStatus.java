package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Pension contract status. Possible values: ACTIVE, TERMINATED, PENSION_PAYMENT, INTERRUPTED,
 * PAYMENTS_SUSPENDED, PAYMENTS_DEFFERED, SETTLED, REPEALED, NEGOTIATED
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum PensionStatus implements HasValue {

    /**
     * Active pension status.
     */
    ACTIVE("ACTIVE"),

    /**
     * Terminated pension status.
     */
    TERMINATED("TERMINATED"),

    /**
     * Pension payment pension status.
     */
    PENSION_PAYMENT("PENSION_PAYMENT"),

    /**
     * Interrupted pension status.
     */
    INTERRUPTED("INTERRUPTED"),

    /**
     * Payments suspended pension status.
     */
    PAYMENTS_SUSPENDED("PAYMENTS_SUSPENDED"),

    /**
     * Payments deferred pension status.
     */
    PAYMENTS_DEFERRED("PAYMENTS_DEFERRED"),

    /**
     * Settled pension status.
     */
    SETTLED("SETTLED"),

    /**
     * Repealed pension status.
     */
    REPEALED("REPEALED"),

    /**
     * Negotiated pension status.
     */
    NEGOTIATED("NEGOTIATED"),

    /**
     * Other pension status.
     */
    OTHER(null);

    private String value;

    PensionStatus(String value) {
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

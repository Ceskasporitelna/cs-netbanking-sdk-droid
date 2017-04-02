package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The execution mode defines when or how standing/sweep order will be cancelled, processed the last
 * time. Possible values: UNTIL_DATE (standing order is valid until specific date - field
 * lastExecutionDate), UNTIL_CANCELLATION (standing order is valid forever and must be cancelled by
 * client), AFTER_MAX_ITERATION_EXCEEDED (certain count of executions is specified - field
 * maxIterations) or MAX_AMOUNT_EXCEEDED (maximum amount which can be transferred for this order
 * is specified, if next iteration would exceed this amount it is not executed - field maxAmount).
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum ExecutionMode implements HasValue {

    /**
     * Standing order is valid until specific date - field lastExecutionDate
     */
    UNTIL_DATE("UNTIL_DATE "),

    /**
     * Standing order is valid forever and must be cancelled by client
     */
    UNTIL_CANCELLATION("UNTIL_CANCELLATION"),

    /**
     * Certain count of executions is specified - field maxIterations
     */
    AFTER_MAX_ITERATION_EXCEEDED("AFTER_MAX_ITERATION_EXCEEDED"),

    /**
     * Maximum amount which can be transferred for this order is specified, if next iteration would
     * exceed this amount it is not executed - field maxAmount
     */
    MAX_AMOUNT_EXCEEDED("MAX_AMOUNT_EXCEEDED"),

    /**
     * Other execution mode.
     */
    OTHER(null);

    private String value;

    ExecutionMode(String value) {
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

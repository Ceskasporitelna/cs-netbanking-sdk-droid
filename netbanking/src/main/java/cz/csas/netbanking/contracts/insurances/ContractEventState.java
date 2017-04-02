package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * State of the event. Possible values: REPORTED, ATTACHING_DOCS, IN_SOLUTION, CLOSED
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum ContractEventState implements HasValue {

    /**
     * Reported contract event state.
     */
    REPORTED("REPORTED"),

    /**
     * Attaching docs contract event state.
     */
    ATTACHING_DOCS("ATTACHING_DOCS"),

    /**
     * In solution contract event state.
     */
    IN_SOLUTION("IN_SOLUTION"),

    /**
     * Closed contract event state.
     */
    CLOSED("CLOSED"),

    /**
     * Other contract event state.
     */
    OTHER(null);

    private String value;

    ContractEventState(String value) {
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

package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Status of the insurance contract: ACTIVE or CLOSED
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum InsuranceStatus implements HasValue {

    /**
     * Active insurance status.
     */
    ACTIVE("ACTIVE"),

    /**
     * Closed insurance status.
     */
    CLOSED("CLOSED"),

    /**
     * Other insurance status.
     */
    OTHER(null);

    private String value;

    InsuranceStatus(String value) {
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

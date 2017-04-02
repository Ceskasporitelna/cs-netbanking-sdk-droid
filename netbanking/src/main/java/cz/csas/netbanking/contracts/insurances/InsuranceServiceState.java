package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Indicates service state. Three possible values: ACTIVATED - insurance was already activated but
 * will be active in the future. ACTIVE - insurance is active right now. INACTIVE - insurance is
 * neither activated nor active.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public enum InsuranceServiceState implements HasValue {

    /**
     * Activated insurance service state.
     */
    ACTIVATED("ACTIVATED"),

    /**
     * Active insurance service state.
     */
    ACTIVE("ACTIVE"),

    /**
     * Inactive insurance service state.
     */
    INACTIVE("INACTIVE"),

    /**
     * Other insurance service state.
     */
    OTHER(null);

    private String value;

    InsuranceServiceState(String value) {
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

package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Entitlement type. Possible values TAKEOVER.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum Entitlement implements HasValue {

    /**
     * Take over entitlement.
     */
    TAKE_OVER("TAKEOVER"),

    /**
     * Other entitlement.
     */
    OTHER(null);

    private String value;

    Entitlement(String value) {
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

package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Standing order type - either STANDING_ORDER (there is fixed amount specified which is transferred
 * in defined times) or SWEEP_ORDER (there is specified limit, amount over limit/to limit is
 * transferred in defined times).
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum StandingOrderType implements HasValue {

    /**
     * Standing order - there is fixed amount specified which is transferred in defined times
     */
    STANDING_ORDER("STANDING_ORDER"),

    /**
     * Sweep order - there is specified limit, amount over limit/to limit is transferred in defined
     * times
     */
    SWEEP_ORDER("SWEEP_ORDER"),

    /**
     * Other standing order type.
     */
    OTHER(null);

    private String value;

    StandingOrderType(String value) {
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

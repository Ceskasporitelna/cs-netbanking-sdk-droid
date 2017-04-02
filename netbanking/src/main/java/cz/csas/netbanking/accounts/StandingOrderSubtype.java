package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Relevant only for sweep orders. Either SWEEP_OVER_LIMIT (amount over limit is transferred from
 * account) or SWEEP_UNDER_LIMIT (amount to limit is transferred to account).
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum StandingOrderSubtype implements HasValue {

    /**
     * Amount over limit is transferred from account
     */
    SWEEP_OVER_LIMIT("SWEEP_OVER_LIMIT"),

    /**
     * Amount to limit is transferred to account
     */
    SWEEP_UNDER_LIMIT("SWEEP_UNDER_LIMIT"),

    /**
     * Other standing order subtype.
     */
    OTHER(null);

    private String value;

    StandingOrderSubtype(String value) {
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

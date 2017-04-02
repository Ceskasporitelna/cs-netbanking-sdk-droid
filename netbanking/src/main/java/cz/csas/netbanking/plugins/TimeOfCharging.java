package cz.csas.netbanking.plugins;

import cz.csas.cscore.webapi.HasValue;

/**
 * Time moment of changing the plugin fee. Possible values are IMMEDIATELY, ACCOUNT_STATEMENT,
 * UNKNOWN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum TimeOfCharging implements HasValue {

    /**
     * Immediately time of charging.
     */
    IMMEDIATELY("IMMEDIATELY"),

    /**
     * Account statement time of charging.
     */
    ACCOUNT_STATEMENT("ACCOUNT_STATEMENT"),

    /**
     * Unknown time of charging.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other time of charging.
     */
    OTHER(null);

    private String value;

    TimeOfCharging(String value) {
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

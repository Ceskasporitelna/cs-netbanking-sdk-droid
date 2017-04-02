package cz.csas.netbanking.plugins;

import cz.csas.cscore.webapi.HasValue;

/**
 * Frequency period of changing the plugin fee. Possible values are MONTHLY, NON_RECURRING, UNKNOWN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum PeriodOfCharging implements HasValue {

    /**
     * Monthly period of charging.
     */
    MONTHLY("MONTHLY"),

    /**
     * Non recurring period of charging.
     */
    NON_RECURRING("NON_RECURRING"),

    /**
     * Unknown period of charging.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other period of charging.
     */
    OTHER(null);

    private String value;

    PeriodOfCharging(String value) {
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

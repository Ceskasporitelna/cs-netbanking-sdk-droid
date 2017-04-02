package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Possible months where there is no payment
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.09.16.
 */
public enum StoppageMonth implements HasValue {

    /**
     * January
     */
    JANUARY("JANUARY"),

    /**
     * February
     */
    FEBRUARY("FEBRUARY"),

    /**
     * March
     */
    MARCH("MARCH"),

    /**
     * April
     */
    APRIL("APRIL"),

    /**
     * May
     */
    MAY("MAY"),

    /**
     * June
     */
    JUNE("JUNE"),

    /**
     * July
     */
    JULY("JULY"),

    /**
     * August
     */
    AUGUST("AUGUST"),

    /**
     * September
     */
    SEPTEMBER("SEPTEMBER"),

    /**
     * October
     */
    OCTOBER("OCTOBER"),

    /**
     * November
     */
    NOVEMBER("NOVEMBER"),

    /**
     * December
     */
    DECEMBER("DECEMBER");

    private String value;

    StoppageMonth(String value) {
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

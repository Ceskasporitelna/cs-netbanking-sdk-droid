package cz.csas.netbanking;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Periodicity.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum Periodicity implements HasValue {

    /**
     * Daily periodicity.
     */
    DAILY("DAILY"),

    /**
     * Weekly periodicity.
     */
    WEEKLY("WEEKLY"),

    /**
     * Bi weekly periodicity.
     */
    BI_WEEKLY("BI_WEEKLY"),

    /**
     * Monthly periodicity.
     */
    MONTHLY("MONTHLY"),

    /**
     * Quarterly periodicity.
     */
    QUARTERLY("QUARTERLY"),

    /**
     * Halfyearly periodicity.
     */
    HALFYEARLY("HALFYEARLY"),

    /**
     * Yearly periodicity.
     */
    YEARLY("YEARLY"),

    /**
     * Ten yaerly periodicity.
     */
    TEN_YAERLY("10_YAERLY"),

    /**
     * Other periodicity.
     */
    OTHER("OTHER");

    private String value;

    /**
     * Instantiates a new Periodicity.
     *
     * @param value the value
     */
    Periodicity(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

}

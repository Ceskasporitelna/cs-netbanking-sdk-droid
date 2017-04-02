package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card limit period.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardLimitPeriod implements HasValue {

    /**
     * One d card limit period.
     */
    ONE_D("1D"),

    /**
     * Two d card limit period.
     */
    TWO_D("2D"),

    /**
     * Three d card limit period.
     */
    THREE_D("3D"),

    /**
     * Five d card limit period.
     */
    FIVE_D("5D"),

    /**
     * Seven d card limit period.
     */
    SEVEN_D("7D"),

    /**
     * Ten d card limit period.
     */
    TEN_D("10D"),

    /**
     * Fifteen d card limit period.
     */
    FIFTEEN_D("15D"),

    /**
     * Thirty d card limit period.
     */
    THIRTY_D("30D"),

    /**
     * Other card limit period.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card limit period.
     *
     * @param value the value
     */
    CardLimitPeriod(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

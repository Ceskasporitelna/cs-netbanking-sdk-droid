package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card delivery mode.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardDeliveryMode implements HasValue {

    /**
     * Branch card delivery mode.
     */
    BRANCH("BRANCH"),

    /**
     * Home card delivery mode.
     */
    HOME("HOME"),

    /**
     * Other branch card delivery mode.
     */
    OTHER_BRANCH("OTHER_BRANCH"),

    /**
     * Address abroad card delivery mode.
     */
    ADDRESS_ABROAD("ADDRESS_ABROAD"),

    /**
     * Other card delivery mode.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card delivery mode.
     *
     * @param value the value
     */
    CardDeliveryMode(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

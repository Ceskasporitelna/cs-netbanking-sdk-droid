package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card provider.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardProvider implements HasValue {

    /**
     * Erste bank card provider.
     */
    ERSTE_BANK("ERSTE_BANK"),

    /**
     * External card provider.
     */
    EXTERNAL("EXTERNAL"),

    /**
     * Other card provider.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card provider.
     *
     * @param value the value
     */
    CardProvider(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

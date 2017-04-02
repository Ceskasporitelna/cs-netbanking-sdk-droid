package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardType implements HasValue {

    /**
     * Bank card card type.
     */
    BANK_CARD("BANK_CARD"),

    /**
     * Credit card type.
     */
    CREDIT("CREDIT"),

    /**
     * Other card type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card type.
     *
     * @param value the value
     */
    CardType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

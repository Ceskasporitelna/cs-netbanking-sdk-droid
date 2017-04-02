package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card action.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardAction implements HasValue {

    /**
     * Reissue pin card action.
     */
    REISSUE_PIN("REISSUE_PIN"),

    /**
     * Lock card card action.
     */
    LOCK_CARD("LOCK_CARD"),

    /**
     * Unlock card card action.
     */
    UNLOCK_CARD("UNLOCK_CARD"),

    /**
     * Replace card card action.
     */
    REPLACE_CARD("REPLACE_CARD"),

    /**
     * Activate card card action.
     */
    ACTIVATE_CARD("ACTIVATE_CARD"),

    /**
     * Set automatic replacement on card action.
     */
    SET_AUTOMATIC_REPLACEMENT_ON("SET_AUTOMATIC_REPLACEMENT_ON"),

    /**
     * Set automatic replacement off card action.
     */
    SET_AUTOMATIC_REPLACEMENT_OFF("SET_AUTOMATIC_REPLACEMENT_OFF"),

    /**
     * Other card action.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card action.
     *
     * @param value the value
     */
    CardAction(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

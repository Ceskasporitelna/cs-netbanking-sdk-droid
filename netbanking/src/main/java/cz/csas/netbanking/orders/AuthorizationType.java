package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Authorization type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum AuthorizationType implements HasValue {

    /**
     * Tac limit authorization type.
     */
    TAC("TAC"),

    /**
     * Tan limit authorization type.
     */
    TAN("TAN"),

    /**
     * Sms limit authorization type.
     */
    SMS("SMS"),

    /**
     * Grid card limit authorization type.
     */
    GRID_CARD("GRID_CARD"),

    /**
     * Eok limit authorization type.
     */
    EOK("EOK"),

    /**
     * Display card limit authorization type.
     */
    DISPLAY_CARD("DISPLAY_CARD"),

    /**
     * Mtoken limit authorization type.
     */
    MTOKEN("M_TOKEN"),

    /**
     * Other limit authorization type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Limit authorization type.
     *
     * @param value the value
     */
    AuthorizationType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

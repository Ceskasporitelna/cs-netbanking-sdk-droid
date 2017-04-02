package cz.csas.netbanking.promotions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the action button. Possible values are SHOPPRODUCT, SHOWURL, HIDE
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum ActionType implements HasValue {

    /**
     * Shopproduct action type.
     */
    SHOPPRODUCT("SHOPPRODUCT"),

    /**
     * Showurl action type.
     */
    SHOWURL("SHOWURL"),

    /**
     * Hide action type.
     */
    HIDE("HIDE"),

    /**
     * Other action type.
     */
    OTHER(null);

    private String value;

    ActionType(String value) {
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

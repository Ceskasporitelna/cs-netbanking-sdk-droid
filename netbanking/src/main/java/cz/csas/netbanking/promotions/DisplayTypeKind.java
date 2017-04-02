package cz.csas.netbanking.promotions;

import cz.csas.cscore.webapi.HasValue;

/**
 * The type of the layout for the campaign. Currently only these values are possible: OVERVIEW_CARD
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum DisplayTypeKind implements HasValue {

    /**
     * Overview card display type kind.
     */
    OVERVIEW_CARD("OVERVIEW_CARD"),

    /**
     * Other display type kind.
     */
    OTHER(null);

    private String value;

    DisplayTypeKind(String value) {
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

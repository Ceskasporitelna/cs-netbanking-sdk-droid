package cz.csas.netbanking.templates;

import cz.csas.cscore.webapi.HasValue;

/**
 * Order category. Possible values: DOMESTIC, INTERNATIONAL
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum OrderCategory implements HasValue {

    /**
     * Domestic order category.
     */
    DOMESTIC("DOMESTIC"),

    /**
     * International order category.
     */
    INTERNATIONAL("INTERNATIONAL"),

    /**
     * Other order category.
     */
    OTHER(null);

    private String value;

    OrderCategory(String value) {
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

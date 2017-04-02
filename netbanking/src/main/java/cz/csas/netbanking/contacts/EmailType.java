package cz.csas.netbanking.contacts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Email type. ENUM values: PRIVATE, COMPANY, UNKNOWN
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 09.09.16.
 */
public enum EmailType implements HasValue {

    /**
     * Private email type.
     */
    PRIVATE("PRIVATE"),

    /**
     * Company email type.
     */
    COMPANY("COMPANY"),

    /**
     * Unknown email type.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other email type.
     */
    OTHER(null);

    private String value;

    EmailType(String value) {
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

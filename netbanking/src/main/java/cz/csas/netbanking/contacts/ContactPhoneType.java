package cz.csas.netbanking.contacts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Phone type. Possible values: PRIVATE, COMPANY, UNKNOWN
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public enum ContactPhoneType implements HasValue {

    /**
     * Private contact phone type.
     */
    PRIVATE("PRIVATE"),

    /**
     * Company contact phone type.
     */
    COMPANY("COMPANY"),

    /**
     * Unknown contact phone type.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other contact phone type.
     */
    OTHER(null);

    private String value;

    ContactPhoneType(String value) {
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

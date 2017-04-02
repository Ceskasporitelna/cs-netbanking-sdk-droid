package cz.csas.netbanking.contacts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of contact. ENUM values: ADDRESS, PHONE, EMAIL, FAX.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public enum ContactType implements HasValue {

    /**
     * Address contact type.
     */
    ADDRESS("ADDRESS"),

    /**
     * Phone contact type.
     */
    PHONE("PHONE"),

    /**
     * Email contact type.
     */
    EMAIL("EMAIL"),

    /**
     * Fax contact type.
     */
    FAX("FAX"),

    /**
     * Other contact type.
     */
    OTHER(null);

    private String value;

    ContactType(String value) {
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

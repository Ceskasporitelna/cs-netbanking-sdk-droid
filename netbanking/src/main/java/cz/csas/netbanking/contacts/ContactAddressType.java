package cz.csas.netbanking.contacts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Address type. Possible values: PERMANENT_RESIDENCE, SECONDARY_RESIDENCE, COMPANY_RESIDENCE,
 * UNKNOWN
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public enum ContactAddressType implements HasValue {

    /**
     * Permanent residence contact address type.
     */
    PERMANENT_RESIDENCE("PERMANENT_RESIDENCE"),

    /**
     * Secondary residence contact address type.
     */
    SECONDARY_RESIDENCE("SECONDARY_RESIDENCE"),

    /**
     * Company residence contact address type.
     */
    COMPANY_RESIDENCE("COMPANY_RESIDENCE"),

    /**
     * Unknown contact address type.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other contact address type.
     */
    OTHER(null);

    private String value;

    ContactAddressType(String value) {
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

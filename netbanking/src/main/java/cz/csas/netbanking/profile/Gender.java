package cz.csas.netbanking.profile;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Gender.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum Gender implements HasValue {

    /**
     * Male gender.
     */
    MALE("MALE"),

    /**
     * Female gender.
     */
    FEMALE("FEMALE"),

    /**
     * Unknown gender.
     */
    UNKNOWN("UNKNOWN");

    private String value;

    /**
     * Instantiates a new Gender.
     *
     * @param value the value
     */
    Gender(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}

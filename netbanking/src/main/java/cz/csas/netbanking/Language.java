package cz.csas.netbanking;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Language.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum Language implements HasValue {

    /**
     * En language.
     */
    EN("en"),

    /**
     * De language.
     */
    DE("de"),

    /**
     * Cs language.
     */
    CS("cs"),

    /**
     * Sk language.
     */
    SK("sk"),

    /**
     * Hr language.
     */
    HR("hr"),

    /**
     * Sr language.
     */
    SR("sr"),

    /**
     * Ro language.
     */
    RO("ro"),

    /**
     * Hu language.
     */
    HU("hu"),

    /**
     * Fr language.
     */
    FR("fr"),

    /**
     * Other language.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Language.
     *
     * @param value the value
     */
    Language(String value) {
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

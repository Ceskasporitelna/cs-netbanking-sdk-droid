package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Secure settings status.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum SecureSettingsStatus implements HasValue {

    /**
     * Ok secure settings status.
     */
    OK("OK"),

    /**
     * Not activated secure settings status.
     */
    NOT_ACTIVATED("NOT_ACTIVATED"),

    /**
     * Other secure settings status.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Secure settings status.
     *
     * @param value the value
     */
    SecureSettingsStatus(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

}

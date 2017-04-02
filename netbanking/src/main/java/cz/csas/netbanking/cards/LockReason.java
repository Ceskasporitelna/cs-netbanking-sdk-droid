package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Lock reason.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum LockReason implements HasValue {

    /**
     * Theft lock reason.
     */
    THEFT("THEFT"),

    /**
     * Loss lock reason.
     */
    LOSS("LOSS"),

    /**
     * Fraud lock reason.
     */
    FRAUD("FRAUD"),

    /**
     * Other lock reason.
     */
    OTHER("OTHER");

    private String value;

    /**
     * Instantiates a new Lock reason.
     *
     * @param value the value
     */
    LockReason(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

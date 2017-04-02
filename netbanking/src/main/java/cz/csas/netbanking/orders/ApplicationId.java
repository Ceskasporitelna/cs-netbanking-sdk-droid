package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Application id.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum ApplicationId implements HasValue {

    /**
     * George application id.
     */
    GEORGE("GEORGE"),

    /**
     * Atm payment applicationl id.
     */
    ATM_PAYMENT("ATM_PAYMENT"),

    /**
     * Atm other applicationl id.
     */
    ATM_OTHER("ATM_OTHER"),

    /**
     * Gsm applicationl id.
     */
    GSM("GSM"),

    /**
     * Branch fe applicationl id.
     */
    BRANCH_FE("BRANCH_FE"),

    /**
     * Post office applicationl id.
     */
    POST_OFFICE("POST_OFFICE"),

    /**
     * Internet banking applicationl id.
     */
    INTERNET_BANKING("INTERNET_BANKING"),

    /**
     * Telephone banker applicationl id.
     */
    TELEPHONE_BANKER("TELEPHONE_BANKER"),

    /**
     * Collection box applicationl id.
     */
    COLLECTION_BOX("COLLECTION_BOX"),

    /**
     * Video banker applicationl id.
     */
    VIDEO_BANKER("VIDEO_BANKER"),

    /**
     * Unknown applicationl id.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other applicationl id.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Applicationl id.
     *
     * @param value the value
     */
    ApplicationId(String value) {
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

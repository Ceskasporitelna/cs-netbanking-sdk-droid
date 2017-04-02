package cz.csas.netbanking.profile;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Marketing info acceptance.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum MarketingInfoAcceptance implements HasValue {

    /**
     * Accepted marketing info acceptance.
     */
    ACCEPTED("ACCEPTED"),

    /**
     * Not accepted marketing info acceptance.
     */
    NOT_ACCEPTED("NOT_ACCEPTED"),

    /**
     * Unknown marketing info acceptance.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other marketing info acceptance.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Marketing info acceptance.
     *
     * @param value the value
     */
    MarketingInfoAcceptance(String value) {
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

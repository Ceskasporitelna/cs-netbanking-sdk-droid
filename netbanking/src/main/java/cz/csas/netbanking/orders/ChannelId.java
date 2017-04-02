package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Channel id.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum ChannelId implements HasValue {

    /**
     * Net banking channel id.
     */
    NET_BANKING("NET_BANKING"),

    /**
     * Atm channel id.
     */
    ATM("ATM"),

    /**
     * Mobile banking channel id.
     */
    MOBILE_BANKING("MOBILE_BANKING"),

    /**
     * Branch channel id.
     */
    BRANCH("BRANCH"),

    /**
     * Post office channel id.
     */
    POST_OFFICE("POST_OFFICE"),

    /**
     * Call centre channel id.
     */
    CALL_CENTRE("CALL_CENTRE"),

    /**
     * Other channel id.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Channel id.
     *
     * @param value the value
     */
    ChannelId(String value) {
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

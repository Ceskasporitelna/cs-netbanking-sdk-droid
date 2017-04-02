package cz.csas.netbanking.authorizationLimits;

import cz.csas.cscore.webapi.HasValue;

/**
 * ID of the channel for which is limit defined. Possible values: netBanking, mobileBanking,
 * homeBanking, thirdParty, and unknown - limit valid for all channels, not particularly defined.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum ChannelId implements HasValue {

    /**
     * Net banking channel id.
     */
    NET_BANKING("NET_BANKING"),

    /**
     * Mobile banking channel id.
     */
    MOBILE_BANKING("MOBILE_BANKING"),

    /**
     * Home banking channel id.
     */
    HOME_BANKING("HOME_BANKING"),

    /**
     * Third party channel id.
     */
    THIRD_PARTY("THIRD_PARTY"),

    /**
     * Unknown channel id.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other channel id.
     */
    OTHER(null);

    private String value;

    ChannelId(String value) {
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

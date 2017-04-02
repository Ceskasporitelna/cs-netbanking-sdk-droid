package cz.csas.netbanking.authorizationLimits;

import cz.csas.cscore.webapi.HasValue;

/**
 * ID of the application for which limit is defined. Possible values: GEORGE, INTERNET_BANKING and
 * UNKNOWN - limit valid for all applications, not particularly defined.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum ApplicationId implements HasValue {

    /**
     * George application id.
     */
    GEORGE("GEORGE"),

    /**
     * Internet banking application id.
     */
    INTERNET_BANKING("INTERNET_BANKING"),

    /**
     * Unknown application id.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other application id.
     */
    OTHER(null);

    private String value;

    ApplicationId(String value) {
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

package cz.csas.netbanking.authorizationLimits;

import java.util.Map;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.webapi.Parameters;

/**
 * The type Authorization limits parameters.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class AuthorizationLimitsParameters extends Parameters {

    private static final String PARAM_CHANNEL = "channel";

    private ApplicationIdParameter applicationId;

    private String applicationIdRaw;

    /**
     * Instantiates a new Authorization limits parameters.
     *
     * @param applicationId the application id
     */
    public AuthorizationLimitsParameters(ApplicationIdParameter applicationId) {
        this.applicationIdRaw = applicationId.getValue();
    }

    /**
     * Instantiates a new Authorization limits parameters.
     *
     * @param applicationId the application id
     */
    public AuthorizationLimitsParameters(String applicationId) {
        this.applicationIdRaw = applicationId;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> map = super.toDictionary();
        if (this.applicationIdRaw != null) {
            map.put(PARAM_CHANNEL, this.applicationIdRaw);
        }
        return map;
    }

    /**
     * Get application id.
     *
     * @return the application id
     */
    public ApplicationIdParameter getApplicationId() {
        if (applicationId == null && applicationIdRaw != null)
            applicationId = EnumUtils.translateToEnum(ApplicationIdParameter.class, applicationIdRaw);
        return applicationId;
    }

    /**
     * Get application id raw.
     *
     * @return the application id raw
     */
    public String getApplicationIdRaw() {
        return applicationIdRaw;
    }

    /**
     * Set application id.
     *
     * @param applicationId the application id
     */
    public void setApplicationId(ApplicationIdParameter applicationId) {
        this.applicationIdRaw = applicationId.getValue();
    }

    /**
     * Set application id.
     *
     * @param applicationId the application id
     */
    public void setApplicationId(String applicationId) {
        this.applicationIdRaw = applicationId;
    }


}

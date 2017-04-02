package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Supplementary.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Supplementary extends WebApiEntity {

    @CsExpose
    private String email;

    @CsExpose
    private String sms;

    @CsExpose
    private Boolean maxService;

    @CsExpose
    private Boolean optService;

    /**
     * Email used for electronic communication.
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Phone number used for sms communication.
     * @return the sms number
     */
    public String getSms() {
        return sms;
    }

    /**
     * Indication whether maximum service is set up.
     * @return the max service boolean
     */
    public Boolean getMaxService() {
        return maxService;
    }

    /**
     * Indication whether optimum service is set up.
     * @return the opt service boolean
     */
    public Boolean getOptService() {
        return optService;
    }
}

package cz.csas.netbanking.contracts.insurances;

import java.util.Date;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Risk sports activation update response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class RiskSportsActivationUpdateResponse extends WebApiEntity implements Signable {

    private SigningObject signingObject;

    @CsExpose
    private String policyNumber;

    @CsExpose
    private String dateFrom;

    @CsExpose
    private String dateTo;

    @CsExpose
    private String phoneNumber;

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }

    /**
     * Get policy number.
     *
     * @return the policy number
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Get date from.
     *
     * @return the date from
     */
    public Date getDateFrom() {
        return TimeUtils.getPlainDate(dateFrom);
    }

    /**
     * Get date to.
     *
     * @return the date to
     */
    public Date getDateTo() {
        return TimeUtils.getPlainDate(dateTo);
    }

    /**
     * Get phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

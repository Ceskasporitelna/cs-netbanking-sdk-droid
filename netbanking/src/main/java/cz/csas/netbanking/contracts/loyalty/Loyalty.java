package cz.csas.netbanking.contracts.loyalty;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Loyalty.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Loyalty extends WebApiEntity {

    private LoyaltyState state;

    @CsExpose
    @CsSerializedName("state")
    private String stateRaw;

    @CsExpose
    private String exportDate;

    @CsExpose
    private Double pointsCount;

    @CsExpose
    private String activationCode;

    /**
     * State of the ibod account. Possible values are REGISTERED, UNREGISTERED, DEACTIVATED_FROM_FSCS.
     *
     * @return the state
     */
    public LoyaltyState getState() {
        if (state == null && stateRaw != null)
            state = EnumUtils.translateToEnum(LoyaltyState.class, stateRaw);
        return state;
    }

    /**
     * Get state raw.
     *
     * @return the state raw
     */
    public String getStateRaw() {
        return stateRaw;
    }

    /**
     * State of the ibod account. Possible values are REGISTERED, UNREGISTERED, DEACTIVATED_FROM_FSCS.
     *
     * @return the export date
     */
    public Date getExportDate() {
        return TimeUtils.getISO8601Date(exportDate);
    }

    /**
     * IBod points count.
     *
     * @return the points count
     */
    public Double getPointsCount() {
        return pointsCount;
    }

    /**
     * Activation ibod code.
     *
     * @return the activation code
     */
    public String getActivationCode() {
        return activationCode;
    }
}

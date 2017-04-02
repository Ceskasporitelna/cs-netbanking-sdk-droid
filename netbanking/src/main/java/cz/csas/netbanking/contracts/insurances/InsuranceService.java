package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Insurance service.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceService extends WebApiEntity {

    @CsExpose
    private String group;

    @CsExpose
    private String id;

    @CsExpose
    private String iconGroup;

    @CsExpose
    private String nameI18N;

    @CsExpose
    private String descriptionI18N;

    @CsExpose
    private Double availableDays;

    @CsExpose
    private String activeFrom;

    @CsExpose
    private String activeTo;

    @CsExpose
    private Amount bonusAmount;

    private InsuranceServiceState state;

    @CsExpose
    @CsSerializedName("state")
    private String stateRaw;

    /**
     * Indicator for FE for grouping services to boxes. Possible values: RISK_SPORTS, SERVICE
     *
     * @return the group
     */
    public String getGroup() {
        return group;
    }

    /**
     * Service id
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Service icon group
     *
     * @return the icon group
     */
    public String getIconGroup() {
        return iconGroup;
    }

    /**
     * Service name
     *
     * @return the name i 18 n
     */
    public String getNameI18N() {
        return nameI18N;
    }

    /**
     * Description of the service.
     *
     * @return the description i 18 n
     */
    public String getDescriptionI18N() {
        return descriptionI18N;
    }

    /**
     * Relevant only for RISK_SPORTS group. For those number of days this service can be activated this year at all.
     *
     * @return the available days
     */
    public Double getAvailableDays() {
        return availableDays;
    }

    /**
     * Starting date of active service. Relevant for RISK_SPORTS.
     *
     * @return the active from
     */
    public String getActiveFrom() {
        return activeFrom;
    }

    /**
     * Ending date of active service. Relevant for RISK_SPORTS.
     *
     * @return the active to
     */
    public String getActiveTo() {
        return activeTo;
    }

    /**
     * Amount of bonus. Relevant for NO_CLAIM_BONUS, LOYALTY_BONUS.
     *
     * @return the bonus amount
     */
    public Amount getBonusAmount() {
        return bonusAmount;
    }

    /**
     * Indicates service state. Three possible values: ACTIVATED - insurance was already activated
     * but will be active in the future. ACTIVE - insurance is active right now. INACTIVE -
     * insurance is neither activated nor active.
     *
     * @return the state
     */
    public InsuranceServiceState getState() {
        if (state == null && stateRaw != null)
            state = EnumUtils.translateToEnum(InsuranceServiceState.class, stateRaw);
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
}

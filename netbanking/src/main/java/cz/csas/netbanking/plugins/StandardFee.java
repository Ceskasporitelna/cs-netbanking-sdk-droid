package cz.csas.netbanking.plugins;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Standard fee.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class StandardFee {

    private TimeOfCharging timeOfCharging;

    @CsExpose
    @CsSerializedName("timeOfCharging")
    private String timeOfChargingRaw;

    private PeriodOfCharging periodOfCharging;

    @CsExpose
    @CsSerializedName("periodOfCharging")
    private String periodOfChargingRaw;

    @CsExpose
    private Amount amount;

    /**
     * Time moment of changing the plugin fee. Possible values are IMMEDIATELY, ACCOUNT_STATEMENT,
     * UNKNOWN.
     *
     * @return the time of charging
     */
    public TimeOfCharging getTimeOfCharging() {
        if (timeOfCharging == null && timeOfChargingRaw != null)
            timeOfCharging = EnumUtils.translateToEnum(TimeOfCharging.class, timeOfChargingRaw);
        return timeOfCharging;
    }

    /**
     * Get time of charging raw.
     *
     * @return the time of charging raw
     */
    public String getTimeOfChargingRaw() {
        return timeOfChargingRaw;
    }

    /**
     * Frequency period of changing the plugin fee. Possible values are MONTHLY, NON_RECURRING,
     * UNKNOWN.
     *
     * @return the period of charging
     */
    public PeriodOfCharging getPeriodOfCharging() {
        if (periodOfCharging == null && periodOfChargingRaw != null)
            periodOfCharging = EnumUtils.translateToEnum(PeriodOfCharging.class, periodOfChargingRaw);
        return periodOfCharging;
    }

    /**
     * Get period of charging raw.
     *
     * @return the period of charging raw
     */
    public String getPeriodOfChargingRaw() {
        return periodOfChargingRaw;
    }

    /**
     * Fee amount defined for this plugin.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }
}

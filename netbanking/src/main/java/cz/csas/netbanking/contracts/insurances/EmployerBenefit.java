package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Employer benefit.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class EmployerBenefit {

    private EmployerBenefitType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private Amount amount;

    private Interval frequency;

    @CsExpose
    @CsSerializedName("frequency")
    private String frequencyRaw;

    /**
     * Explanatory text to employer contribution. Possible values: WHOLE_PREMIUM, PARTLY_PAID_PREMIUM, EXTRAORDINARY_PAYMENTS?
     *
     * @return the type
     */
    public EmployerBenefitType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(EmployerBenefitType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Amount of the contribution
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * frequency of the contribution. ENUM: ONCE, MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, UNKNOWN
     *
     * @return the frequency
     */
    public Interval getFrequency() {
        if (frequency == null && frequencyRaw != null)
            frequency = EnumUtils.translateToEnum(Interval.class, frequencyRaw);
        return frequency;
    }

    /**
     * Get frequency raw.
     *
     * @return the frequency raw
     */
    public String getFrequencyRaw() {
        return frequencyRaw;
    }
}

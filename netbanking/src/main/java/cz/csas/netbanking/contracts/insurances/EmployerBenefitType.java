package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Explanatory text to employer contribution. Possible values: WHOLE_PREMIUM, PARTLY_PAID_PREMIUM,
 * EXTRAORDINARY_PAYMENTS
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum EmployerBenefitType implements HasValue {

    /**
     * Whole premium employer benefit type.
     */
    WHOLE_PREMIUM("WHOLE_PREMIUM"),

    /**
     * Partly paid premium employer benefit type.
     */
    PARTLY_PAID_PREMIUM("PARTLY_PAID_PREMIUM"),

    /**
     * Extraordinary payments employer benefit type.
     */
    EXTRAORDINARY_PAYMENTS("EXTRAORDINARY_PAYMENTS"),

    /**
     * Other employer benefit type.
     */
    OTHER(null);

    private String value;

    EmployerBenefitType(String value) {
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

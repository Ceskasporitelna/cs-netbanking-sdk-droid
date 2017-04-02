package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Product Type of insurance. ENUM values: LIFE (CSAS supports only this value)
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum InsuranceType implements HasValue {

    /**
     * Life insurance type.
     */
    LIFE("LIFE"),

    /**
     * Other insurance type.
     */
    OTHER("OTHER");

    private String value;

    InsuranceType(String value) {
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

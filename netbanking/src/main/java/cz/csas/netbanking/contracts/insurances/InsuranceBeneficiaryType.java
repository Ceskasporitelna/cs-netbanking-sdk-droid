package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of beneficiary
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum InsuranceBeneficiaryType implements HasValue {

    /**
     * Person insurance beneficiary type.
     */
    PERSON("PERSON"),

    /**
     * Other insurance beneficiary type.
     */
    OTHER(null);

    private String value;

    InsuranceBeneficiaryType(String value) {
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

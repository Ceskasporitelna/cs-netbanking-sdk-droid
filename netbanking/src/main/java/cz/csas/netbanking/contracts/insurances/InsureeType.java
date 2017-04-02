package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of person related to the insurance contract. 3 possible values: POLICYHOLDER,
 * INSURED_PERSON, CHILD.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum InsureeType implements HasValue {

    /**
     * Policy holder insuree type.
     */
    POLICY_HOLDER("POLICYHOLDER"),

    /**
     * Insured person insuree type.
     */
    INSURED_PERSON("INSURED_PERSON"),

    /**
     * Child insuree type.
     */
    CHILD("CHILD"),

    /**
     * Other insuree type.
     */
    OTHER(null);

    private String value;

    InsureeType(String value) {
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

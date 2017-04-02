package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Identification of the product group.
 * Possible values are SUPPLEMENTARY_INSURANCE, PENSION_SAVINGS and SUPPLEMENTARY_SAVINGS.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum PensionSubtype implements HasValue {

    /**
     * Supplementary insurance pension subtype.
     */
    SUPPLEMENTARY_INSURANCE("SUPPLEMENTARY_INSURANCE"),

    /**
     * Pension savings pension subtype.
     */
    PENSION_SAVINGS("PENSION_SAVINGS"),

    /**
     * Supplementary savings pension subtype.
     */
    SUPPLEMENTARY_SAVINGS("SUPPLEMENTARY_SAVINGS"),

    /**
     * Other pension subtype.
     */
    OTHER(null);

    private String value;

    PensionSubtype(String value) {
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

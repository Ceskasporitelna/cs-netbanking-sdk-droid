package cz.csas.netbanking.promotions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Code of the product/plugin connected to the sales promotion. Possible values are Possible values
 * are: RUFO_ORDER, RUFO_INCREASE, UFO_ORDER, UFO_INCREASE.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum ProductCode implements HasValue {

    /**
     * Rufo order product code.
     */
    RUFO_ORDER("RUFO_ORDER"),

    /**
     * Rufo increase product code.
     */
    RUFO_INCREASE("RUFO_INCREASE"),

    /**
     * Ufo order product code.
     */
    UFO_ORDER("UFO_ORDER"),

    /**
     * Ufo increase product code.
     */
    UFO_INCREASE("UFO_INCREASE"),

    /**
     * Pi mobilepayments product code.
     */
    PI_MOBILEPAYMENTS("PI-MOBILEPAYMENTS"),

    /**
     * Other product code.
     */
    OTHER(null);

    private String value;

    ProductCode(String value) {
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

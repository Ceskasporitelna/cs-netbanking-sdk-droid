package cz.csas.netbanking.promotions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the campaign, possible values are PRODUCT_PROMOTION, PLUGIN_PROMOTION, INFOCARD,
 * SHADOWCARD
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum CardDesign implements HasValue {

    /**
     * Product promotion card design.
     */
    PRODUCT_PROMOTION("PRODUCT_PROMOTION"),

    /**
     * Plugin promotion card design.
     */
    PLUGIN_PROMOTION("PLUGIN_PROMOTION"),

    /**
     * Infocard card design.
     */
    INFOCARD("INFOCARD"),

    /**
     * Shadowcard card design.
     */
    SHADOWCARD("SHADOWCARD"),

    /**
     * Other card design.
     */
    OTHER(null);

    private String value;

    CardDesign(String value) {
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

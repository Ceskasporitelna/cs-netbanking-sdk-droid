package cz.csas.netbanking.securities;

import cz.csas.cscore.webapi.HasValue;

/**
 * Security Product Group. Possible values: BONDS_AND_MORE, GUARANTEE_OF_PRINCIPAL,
 * NO_GUARANTEE_OF_PRINCIPAL, REAL_ESTATE, SHARES, STOCK_AND_MIXED, INVESTMENT, KNOCK_OUT, UNKNOWN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum ProductGroup implements HasValue {

    /**
     * Bonds and more product group.
     */
    BONDS_AND_MORE("BONDS_AND_MORE"),

    /**
     * Guarantee of principal product group.
     */
    GUARANTEE_OF_PRINCIPAL("GUARANTEE_OF_PRINCIPAL"),

    /**
     * No guarantee of principal product group.
     */
    NO_GUARANTEE_OF_PRINCIPAL("NO_GUARANTEE_OF_PRINCIPAL"),

    /**
     * Real estate product group.
     */
    REAL_ESTATE("REAL_ESTATE"),

    /**
     * Shares product group.
     */
    SHARES("SHARES"),

    /**
     * Stock and mixed product group.
     */
    STOCK_AND_MIXED("STOCK_AND_MIXED"),

    /**
     * Investment product group.
     */
    INVESTMENT("INVESTMENT"),

    /**
     * Knock out product group.
     */
    KNOCK_OUT("KNOCK_OUT"),

    /**
     * Unknown product group.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other product group.
     */
    OTHER(null);

    private String value;

    ProductGroup(String value) {
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

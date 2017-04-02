package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public enum ProductType implements HasValue {

    /**
     * Current product type.
     */
    CURRENT("CURRENT"),

    /**
     * Saving product type.
     */
    SAVING("SAVING"),

    /**
     * Loan product type.
     */
    LOAN("LOAN"),

    /**
     * Other product type.
     */
    OTHER(null);

    private String value;


    /**
     * Instantiates a new Product type.
     *
     * @param value the value
     */
    ProductType(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

}

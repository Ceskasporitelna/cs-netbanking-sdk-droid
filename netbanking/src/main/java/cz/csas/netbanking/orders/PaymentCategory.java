package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Payment category.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum PaymentCategory implements HasValue {

    /**
     * Domestic order category.
     */
    DOMESTIC("DOMESTIC"),

    /**
     * Own transfer order category.
     */
    OWN_TRANSFER("OWN_TRANSFER"),

    /**
     * Sepa order category.
     */
    SEPA("SEPA"),

    /**
     * International order category.
     */
    INTERNATIONAL("INTERNATIONAL"),

    /**
     * Other order category.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Payment category.
     *
     * @param value the value
     */
    PaymentCategory(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

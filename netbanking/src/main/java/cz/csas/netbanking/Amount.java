package cz.csas.netbanking;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Amount provides information about amount used in several classes.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16. Amounts are objects that include the value, the precision and the currency.
 * When AMOUNT is used as input value, maximum expected precision depends
 * on currency. f.e. currency max.precision JPY	    0 TND	    3 default	2
 */
public class Amount {

    @CsExpose
    private Long value;

    @CsExpose
    private Integer precision;

    @CsExpose
    private String currency;

    /**
     * Instantiates a new Amount.
     *
     * @param value     the value
     * @param precision the precision
     * @param currency  the currency
     */
    public Amount(Long value, Integer precision, String currency) {
        this.value = value;
        this.precision = precision;
        this.currency = currency;
    }

    /**
     * Get value of an amount. Number without decimal part.
     *
     * Example:
     * "value":12345678900,
     *
     * @return the value
     */
    public Long getValue() {
        return value;
    }

    /**
     * Get precision of the amount. How many digits from value fields should be considered to be decimal.
     *
     * Example:
     * "precision":2,
     *
     * @return the precision
     */
    public Integer getPrecision() {
        return precision;
    }

    /**
     * Get currency is in ISO 4217 format (3 capital letters code).
     *
     * Example:
     * "currency":"EUR"
     *
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Set value of an amount. Number without decimal part.
     *
     * Example:
     * "value":12345678900,
     *
     * @param value the value
     */
    public void setValue(Long value) {
        this.value = value;
    }

    /**
     * Set precision of the amount. How many digits from value fields should be considered to be decimal.
     *
     * Example:
     * "precision":2,
     *
     * @param precision the precision
     */
    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    /**
     * Set currency is in ISO 4217 format (3 capital letters code).
     *
     * Example:
     * "currency":"EUR"
     *
     * @param currency the currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}

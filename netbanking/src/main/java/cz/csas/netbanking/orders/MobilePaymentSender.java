package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Mobile payment sender provides information about mobile payment sender.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 17.05.16.
 */
public class MobilePaymentSender extends WebApiEntity {
    @CsExpose
    private String number;

    @CsExpose
    private String bankCode;

    @CsExpose
    private String countryCode;

    @CsExpose
    private String iban;

    @CsExpose
    private String bic;

    /**
     * Instantiates a new Mobile payment sender.
     *
     * @param number      the number
     * @param bankCode    the bank code
     * @param countryCode the country code
     * @param iban        the iban
     * @param bic         the bic
     */
    public MobilePaymentSender(String number, String bankCode, String countryCode, String iban, String bic) {
        this.number = number;
        this.bankCode = bankCode;
        this.countryCode = countryCode;
        this.iban = iban;
        this.bic = bic;
    }

    /**
     * Get account number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Get bank code.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Get country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Get IBAN.
     *
     * @return the IBAN
     */
    public String getIban() {
        return iban;
    }

    /**
     * Get BIC.
     *
     * @return the BIC
     */
    public String getBic() {
        return bic;
    }
}

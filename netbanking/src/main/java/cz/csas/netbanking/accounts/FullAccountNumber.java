package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Account number provides information about account number.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16. Account number comes in two flavors IBAN (IBAN plus optional BIC)
 * identification local account number (BBAN) plus mandatory bank code plus optional country code
 * (in case iban is available along with the local account number optional fields cz-iban and cz-bic are provided)
 * This is due to fact, that using of IBAN format was not adopted in all the Erste group countries.
 * Some applications of ACCOUNTNO are restricted to one of the two
 * flavors only. F.i. when posting new SEPA payment orders sender and receiver accounts must be
 * specified by IBAN format only, or when posting new Domestic payment in CSAS BBAN format should be used.
 * However when requesting existing transactions the receiver account may be returned in
 * format BBAN or IBAN (for SEPA payments). Therefore the ACCOUNTNO object may in some cases
 * only contain IBAN, only local BBAN, or both.
 */
public class FullAccountNumber extends AccountNumber{

    @CsExpose
    private String iban;

    @CsExpose
    private String bic;

    /**
     * Instantiates a new Account number with only number and bank code.
     *
     * @param number   the number
     * @param bankCode the bank code
     */
    public FullAccountNumber(String number, String bankCode) {
        super(number, bankCode);
    }

    /**
     * Instantiates a new Account number.
     *
     * @param number      the number
     * @param bankCode    the bank code
     * @param countryCode the country code
     * @param iban        the iban
     * @param bic         the bic
     */
    public FullAccountNumber(String number, String bankCode, String countryCode, String iban, String bic) {
        super(number, bankCode, countryCode, null, null);
        this.iban = iban;
        this.bic = bic;
    }

    /**
     * Gets IBAN. Based on ISO 13616-1:2007. A valid IBAN consists of all three of the following components:
     * Country Code (2 capital letters)
     * Check digits (2 digits)
     * BBAN (local Basic Bank Account Number consisting of 1-30 characters)
     * <p/>
     * Example:
     * "iban": "CZ172099900000005603"
     *
     * @return the iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * Sets IBAN. Based on ISO 13616-1:2007. A valid IBAN consists of all three of the following components:
     * Country Code (2 capital letters)
     * Check digits (2 digits)
     * BBAN (local Basic Bank Account Number consisting of 1-30 characters)
     * <p/>
     * Example:
     * "iban": "CZ172099900000005603"
     *
     * @param iban the iban
     */
    public void setIban(String iban) {
        this.iban = iban;
    }

    /**
     * Get BIC code (also know as SWIFT ID/code) standard format (based on ISO 9362) has 8 or 11
     * characters, made up of:
     * 4 letters: Institution Code or bank code
     * 2 letters: ISO 3166-1 alpha-2 country code
     * 2 letters or digits: location code
     * 3 letters or digits: branch code, optional (possible default 'XXX' for primary office)
     * <p/>
     * Example:
     * "bic": "GIBACZPX"
     *
     * @return the bic
     */
    public String getBic() {
        return bic;
    }

    /**
     * Set BIC code (also know as SWIFT ID/code) standard format (based on ISO 9362) has 8 or 11
     * characters, made up of:
     * 4 letters: Institution Code or bank code
     * 2 letters: ISO 3166-1 alpha-2 country code
     * 2 letters or digits: location code
     * 3 letters or digits: branch code, optional (possible default 'XXX' for primary office)
     * <p/>
     * Example:
     * "bic": "GIBACZPX"
     *
     * @param bic the bic
     */
    public void setBic(String bic) {
        this.bic = bic;
    }
}

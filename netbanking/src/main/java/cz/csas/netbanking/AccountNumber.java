package cz.csas.netbanking;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Domestic account number provides information about account number.
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
public class AccountNumber {

    @CsExpose
    private String number;

    @CsExpose
    private String bankCode;

    @CsExpose
    private String countryCode;

    @CsExpose
    @CsSerializedName(value = "cz-iban")
    private String czIban;

    @CsExpose
    @CsSerializedName(value = "cz-bic")
    private String czBic;

    /**
     * Instantiates a new Account number with only number and bank code.
     *
     * @param number   the number
     * @param bankCode the bank code
     */
    public AccountNumber(String number, String bankCode) {
        this.number = number;
        this.bankCode = bankCode;
    }

    /**
     * Instantiates a new Account number.
     *
     * @param number      the number
     * @param bankCode    the bank code
     * @param countryCode the country code
     * @param czIban      the cz iban
     * @param czBic       the cz bic
     */
    public AccountNumber(String number, String bankCode, String countryCode, String czIban, String czBic) {
        this.number = number;
        this.bankCode = bankCode;
        this.countryCode = countryCode;
        this.czIban = czIban;
        this.czBic = czBic;
    }

    /**
     * Get account number with possible prefix. Format is "XXXXXX-NNNNNNNNNN" if prefix is not
     * null or "000000". If prefix is not provided then format is "NNNNNNNNNN" without
     * leading zeros.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Set account number with possible prefix. Format is "XXXXXX-NNNNNNNNNN" if prefix is not
     * null or "000000". If prefix is not provided then format is "NNNNNNNNNN" without
     * leading zeros.
     *
     * @param number the number
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Get local bank code used in local bank clearing system, e.g. 5-digit bank code in AT, 4-digit
     * bank code in CZ, SK.
     * <p/>
     * Example:
     * <p/>
     * "bankCode": "20111"     //Erste Bank der oesterreichischen Sparkassen AG
     * "bankCode": "0800"      //Česká spořitelna, a.s.
     * "bankCode": "0900"      //Slovenská sporiteľňa, a.s.
     *
     * @return the bank code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Set local bank code used in local bank clearing system, e.g. 5-digit bank code in AT, 4-digit
     * bank code in CZ, SK.
     * <p/>
     * Example:
     * <p/>
     * "bankCode": "20111"     //Erste Bank der oesterreichischen Sparkassen AG
     * "bankCode": "0800"      //Česká spořitelna, a.s.
     * "bankCode": "0900"      //Slovenská sporiteľňa, a.s.
     *
     * @param bankCode the bank code
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    /**
     * Get country codes are in ISO 3166-1 format, subtype ALPHA-2. This means two letters in uppercase.
     * Mandatory for international orders.
     * <p/>
     * Example:
     * <p/>
     * "countryCode"="AT"
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Set country code. Country codes are in ISO 3166-1 format, subtype ALPHA-2. This means two letters in uppercase.
     * Mandatory for international orders.
     * <p/>
     * Example:
     * <p/>
     * "countryCode"="AT"
     *
     * @param countryCode the country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Based on ISO 13616-1:2007. A valid IBAN consists of all three of the following components:
     * Country Code (2 capital letters)
     * Check digits (2 digits)
     * BBAN (local Basic Bank Account Number consisting of 1-30 characters)
     * <p/>
     * Example:
     * "iban": "CZ172099900000005603"
     *
     * @return the iban
     */
    public String getCzIban() {
        return czIban;
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
     * @param czIban the iban
     */
    public void setCzIban(String czIban) {
        this.czIban = czIban;
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
    public String getCzBic() {
        return czBic;
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
     * @param czBic the bic
     */
    public void setCzBic(String czBic) {
        this.czBic = czBic;
    }
}
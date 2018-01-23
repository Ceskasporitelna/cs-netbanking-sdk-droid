package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 23/01/2018.
 */
public class AccountParty {

    @CsExpose
    private String accountNumber;

    @CsExpose
    private String accountPrefix;

    @CsExpose
    private String bankCode;

    @CsExpose
    private String bic;

    @CsExpose
    private String iban;

    @CsExpose
    private String partyInfo;

    @CsExpose
    private String partyDescription;

    /**
     * account number of transaction party
     * Required
     * f.e. 2812275553
     *
     * @return account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * account prefix of transaction party
     * f.e. 0
     *
     * @return account prefix
     */
    public String getAccountPrefix() {
        return accountPrefix;
    }

    /**
     * Account bank code of transaction party
     * Required
     * f.e. 0800
     *
     * @return bank code
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * Id of bank/branch, constant for domestic accounts in csas
     * Required
     * f.e. GIBACZPX
     *
     * @return bic
     */
    public String getBic() {
        return bic;
    }

    /**
     * Account number of transaction party in IBAN format
     * Required
     * f.e. CZ2908000000002812275553
     *
     * @return iban
     */
    public String getIban() {
        return iban;
    }

    /**
     * Name of transaction party. For ATM transaction, masked card number used in transaction
     * Required
     * f.e. Petr Malý
     *
     * @return party info
     */
    public String getPartyInfo() {
        return partyInfo;
    }

    /**
     * Whole account number including bank of transaction party. For ATM transaction, address of ATM
     * if known. For card transaction, identification (name) of the merchant.
     * Required
     * f.e. 2812275553/0800
     *
     * @return party description
     */
    public String getPartyDescription() {
        return partyDescription;
    }
}

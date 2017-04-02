package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Card main account provides information about card main account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class CardMainAccount {

    @CsExpose
    private String id;

    @CsExpose
    private String holderName;

    @CsExpose
    private AccountNumber accountno;

    /**
     * Get internal ID as reference for account provided by BE.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get full name of the main account's holder.
     *
     * @return the holder name
     */
    public String getHolderName() {
        return holderName;
    }

    /**
     * Main account is credit card shadow account for credit card or linked main current account
     * for bank/debit card.
     *
     * @return the account number
     */
    public AccountNumber getAccountno() {
        return accountno;
    }
}

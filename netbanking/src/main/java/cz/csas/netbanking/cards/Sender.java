package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Sender provides information about sender.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Sender {

    @CsExpose
    private String id;

    @CsExpose
    private AccountNumber accountno;

    /**
     * Instantiates a new Sender.
     *
     * @param id        the id
     * @param accountno the account number
     */
    public Sender(String id, AccountNumber accountno) {
        this.id = id;
        this.accountno = accountno;
    }

    /**
     * Get identification of the source account for the transfer.
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Get account number of the source account for the transfer.
     *
     * @return the account number
     */
    public AccountNumber getAccountno() {
        return accountno;
    }

    /**
     * Set identification of the source account for the transfer.
     *
     * @param id the identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Set account number of the source account for the transfer.
     *
     * @param accountno the account number
     */
    public void setAccountno(AccountNumber accountno) {
        this.accountno = accountno;
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Transfer receiver provides information about Transfer receiver.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public class TransferReceiver {

    @CsExpose
    private String id;

    @CsExpose
    private AccountNumber accountno;

    /**
     * Get identifier of the account which is allowed as a transfer receiver. If id is specified then
     * you can find it among other accounts in GET netbanking/my/accounts response.
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Get account number which is allowed as a transfer receiver.
     *
     * @return the account number
     */
    public AccountNumber getAccountno() {
        return accountno;
    }
}

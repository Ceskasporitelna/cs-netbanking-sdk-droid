package cz.csas.netbanking.orders;

import java.util.Map;

import cz.csas.cscore.webapi.Parameters;

/**
 * Payment order booking date parameters defines account id to search.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class PaymentBookingDateParameters extends Parameters {

    /**
     * The Param account id.
     */
    final String PARAM_ACCOUNT_ID = "accountId";

    /**
     * Account ID
     */
    private String accountId;

    /**
     * Instantiates a new Payment order booking date parameters.
     *
     * @param accountId the account id
     */
    public PaymentBookingDateParameters(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> dictionary = super.toDictionary();
        if (this.accountId != null) {
            dictionary.put(PARAM_ACCOUNT_ID, accountId);
        }
        return dictionary;
    }

    /**
     * Get account id.
     *
     * @return the account id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Set account id.
     *
     * @param accountId the account id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

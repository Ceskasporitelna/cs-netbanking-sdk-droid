package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Transfer type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum AccountsTransferType implements HasValue {

    /**
     * Revolving loan disbursement transfer type.
     */
    REVOLVING_LOAN_DISBURSEMENT("REVOLVING_LOAN_DISBURSEMENT"),

    /**
     * Other transfer type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Transfer type.
     *
     * @param value the value
     */
    AccountsTransferType(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}

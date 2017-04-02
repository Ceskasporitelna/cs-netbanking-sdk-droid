package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Product sub type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum ProductSubType implements HasValue {

    /**
     * Current account product sub type.
     */
    CURRENT_ACCOUNT("CURRENT_ACCOUNT"),

    /**
     * Investment current account product sub type.
     */
    INVESTMENT_CURRENT_ACCOUNT("INVESTMENT_CURRENT_ACCOUNT"),

    /**
     * Giro account product sub type.
     */
    GIRO_ACCOUNT("GIRO_ACCOUNT"),

    /**
     * Giro account old product sub type.
     */
    GIRO_ACCOUNT_OLD("GIRO_ACCOUNT_OLD"),

    /**
     * Foreign account product sub type.
     */
    FOREIGN_ACCOUNT("FOREIGN_ACCOUNT"),

    /**
     * Investment foreign account product sub type.
     */
    INVESTMENT_FOREIGN_ACCOUNT("INVESTMENT_FOREIGN_ACCOUNT"),

    /**
     * Deposit account product sub type.
     */
    DEPOSIT_ACCOUNT("DEPOSIT_ACCOUNT"),

    /**
     * Saving account product sub type.
     */
    SAVING_ACCOUNT("SAVING_ACCOUNT"),

    /**
     * Saving internet product sub type.
     */
    SAVING_INTERNET("SAVING_INTERNET"),

    /**
     * Children passbook product sub type.
     */
    CHILDREN_PASSBOOK("CHILDREN_PASSBOOK"),

    /**
     * Saving cs product sub type.
     */
    SAVING_CS("SAVING_CS"),

    /**
     * Benefit invest product sub type.
     */
    BENEFIT_INVEST("BENEFIT_INVEST"),

    /**
     * Loan account product sub type.
     */
    LOAN_ACCOUNT("LOAN_ACCOUNT"),

    /**
     * Mortgage product sub type.
     */
    MORTGAGE("MORTGAGE"),

    /**
     * Revolving loan product sub type.
     */
    REVOLVING_LOAN("REVOLVING_LOAN"),

    /**
     * Other product sub type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Product sub type.
     *
     * @param value the value
     */
    ProductSubType(String value) {
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
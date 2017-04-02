package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.StatementsResource;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Account. It provides information about Account.
 * Also provide following resources:
 * <br>
 * Resources:<br>
 * <br>
 * {@link SubAccountsResource}
 * {@link AccountBalanceResource}
 * {@link AccountReservationsResource}
 * {@link TransactionsResource}
 * {@link AccountServicesResource}
 * {@link AccountRepaymentsResource}
 * {@link AccountTransferResource}
 * {@link AccountServicesResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public class Account extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private AccountNumber accountno;

    @CsExpose
    private Amount balance;

    private ProductType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    private ProductSubType subtype;

    @CsExpose
    @CsSerializedName("subtype")
    private String subtypeRaw;

    @CsExpose
    private String productI18N;

    @CsExpose
    private String product;

    @CsExpose
    private Double creditInterestRate;

    /**
     * Get the unique product identifier .
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Get account number of this account.
     *
     * @return the account number
     */
    public AccountNumber getAccountno() {
        return accountno;
    }

    /**
     * Get actual account balance. For loans this field contains outstanding principal.
     *
     * @return the balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Get Product type. Possible values are
     * CURRENT (for current accounts),
     * SAVING (for saving accounts),
     * LOAN (for loans)
     *
     * @return the type
     */
    public ProductType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ProductType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Get subtype product. Possible values are mapping based on type.
     * For CURRENT (CURRENT_ACCOUNT, INVESTMENT_CURRENT_ACCOUNT, GIRO_ACCOUNT, GIRO_ACCOUNT_OLD,
     * FOREIGN_ACCOUNT, INVESTMENT_FOREIGN_ACCOUNT),
     * <p>
     * SAVING (DEPOSIT_ACCOUNT, SAVING_ACCOUNT, SAVING_INTERNET, CHILDREN_PASSBOOK,
     * SAVING_CS, BENEFIT_INVEST),
     * <p>
     * LOAN (LOAN_ACCOUNT, MORTGAGE, REVOLVING_LOAN).
     *
     * @return the subtype
     */
    public ProductSubType getSubtype() {
        if (subtype == null && subtypeRaw != null)
            subtype = EnumUtils.translateToEnum(ProductSubType.class, subtypeRaw);
        return subtype;
    }

    /**
     * Get subtype raw.
     *
     * @return the subtype raw
     */
    public String getSubtypeRaw() {
        return subtypeRaw;
    }

    /**
     * Get localized product description.
     *
     * @return the product description
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * Get product code.
     *
     * @return the product code
     */
    public String getProduct() {
        return product;
    }

    /**
     * Get basic credit Interest rate, used for Current and Saving account. Value in percentage,
     * e.g. 0,5 will be displayed as 0,5 %.
     *
     * @return the credit interest rate
     */
    public Double getCreditInterestRate() {
        return creditInterestRate;
    }

    /**
     * Convenience method for getting detail of the account right from the list
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<MainAccount> callback) {
        if (resource instanceof AccountResource)
            ((AccountResource) resource).get(callback);
    }

    /**
     * Convenience method for updating account's details
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(ChangeAccountSettingsRequest request, CallbackWebApi<ChangeAccountSettingsResponse> callback) {
        if (resource instanceof AccountResource)
            ((AccountResource) resource).update(request, callback);
    }

    /**
     * Convenience method for getting sub-accounts
     *
     * @return the sub accounts resource
     */
    public SubAccountsResource getSubAccountsResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getSubAccountsResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's balance resource.
     *
     * @return the balance resource
     */
    public AccountBalanceResource getBalanceResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getBalanceResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's reservations resource.
     *
     * @return the reservations resource
     */
    public AccountReservationsResource getReservationsResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getReservationsResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's transactions resource.
     *
     * @return the transactions resource
     */
    public TransactionsResource getTransactionsResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getTransactionsResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's services resource.
     *
     * @return the services resource
     */
    public AccountServicesResource getServicesResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getServicesResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's repayments resource.
     *
     * @return the repayments resource
     */
    public AccountRepaymentsResource getRepaymentsResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getRepaymentsResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's statements resource.
     *
     * @return the statements resource
     */
    public StatementsResource getStatementsResource() {
        if (resource instanceof AccountResource)
            return ((AccountResource) resource).getStatementsResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's transfer resource.
     *
     * @return the transfer resource
     */
    public AccountTransferResource getTransferResource() {
        if (resource instanceof AccountResource) return
                ((AccountResource) resource).getTransferResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's stanfing orders resource.
     *
     * @return the standing orders resource
     */
    public AccountStandingOrdersResource getStandingOrdersResource() {
        if (resource instanceof AccountResource)
            ((AccountResource) resource).getStandingOrdersResource();
        return null;
    }

    /**
     * Convenience getter for getting accounts's direct debits resource.
     *
     * @return the direct debits resource
     */
    public AccountDirectDebitsResource getDirectDebitsResource() {
        if (resource instanceof AccountResource)
            ((AccountResource) resource).getStandingOrdersResource();
        return null;
    }
}

package cz.csas.netbanking.contracts.buildings;

import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.CzTransactionsResource;

/**
 * The type Buildings contract.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContract extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private AccountNumber accountno;

    @CsExpose
    private String alias;

    private BuildingAccountType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String product;

    @CsExpose
    private String productI18N;

    @CsExpose
    private Amount balance;

    private BuildingsContractStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private List<String> contractHolders;

    @CsExpose
    private Double creditInterestRate;

    @CsExpose
    private Double debitInterestRate;

    @CsExpose
    private BuildingSaving saving;

    @CsExpose
    private Loan loan;

    @CsExpose
    private List<String> flags;


    /**
     * Building saving identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Building saving account number.
     *
     * @return the accountno
     */
    public AccountNumber getAccountno() {
        return accountno;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Type of the account. Possible values are BUILD_SAVING and BUILD_LOAN.
     *
     * @return the building account type
     */
    public BuildingAccountType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(BuildingAccountType.class, typeRaw);
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
     * Gets product.
     *
     * @return the product code
     */
    public String getProduct() {
        return product;
    }

    /**
     * Product name.
     *
     * @return the product name
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * Building savings account balance. For loans outstanding debt is served
     *
     * @return the balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Status of the contract. Possible values are ACTIVE and CLOSED.
     *
     * @return
     */
    public BuildingsContractStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(BuildingsContractStatus.class, statusRaw);
        return status;
    }

    /**
     * Get status raw.
     *
     * @return the status raw
     */
    public String getStatusRaw() {
        return statusRaw;
    }

    /**
     * Debtor. Will only by set for loans.
     *
     * @return the contract holders
     */
    public List<String> getContractHolders() {
        return contractHolders;
    }

    /**
     * Basic credit interest rate, used for building saving deposits. Value in percentage, e.g.
     * 1,5 will be displayed as 1,5%.
     *
     * @return the credit interest rate
     */
    public Double getCreditInterestRate() {
        return creditInterestRate;
    }

    /**
     * Basic debit interest rate, used for building loan. Value in percentage, e.g. 9,5 will be
     * displayed as 9,5%.
     *
     * @return the debt interest rate
     */
    public Double getDebitInterestRate() {
        return debitInterestRate;
    }

    /**
     * Get saving details.
     *
     * @return the saving
     */
    public BuildingSaving getSaving() {
        return saving;
    }

    /**
     * Gets loan details.
     *
     * @return the loan
     */
    public Loan getLoan() {
        return loan;
    }

    /**
     * List of flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Convenience method for fetching contracts detail
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<BuildingsContract> callback) {
        if (resource instanceof BuildingsContractResource)
            ((BuildingsContractResource) resource).get(callback);
    }

    /**
     * Convenience update method for updating contract
     *
     * @param request  the update request
     * @param callback the callback
     */
    public void update(BuildingsContractUpdateRequest request, CallbackWebApi<BuildingsContractUpdateResponse> callback) {
        if (resource instanceof BuildingsContractResource)
            ((BuildingsContractResource) resource).update(request, callback);
    }

    /**
     * Convenience getter for contracts services
     *
     * @return the services resource
     */
    public BuildingsContractsServicesResource getServicesResource() {
        if (resource instanceof BuildingsContractResource)
            return ((BuildingsContractResource) resource).getServicesResource();
        return null;
    }

    /**
     * Convenience getter for contracts transactions
     *
     * @return the services resource
     */
    public CzTransactionsResource getTransactionsResource() {
        if (resource instanceof BuildingsContractResource)
            return ((BuildingsContractResource) resource).getTransactionsResource();
        return null;
    }
}

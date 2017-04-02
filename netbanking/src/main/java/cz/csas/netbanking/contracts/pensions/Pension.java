package cz.csas.netbanking.contracts.pensions;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Pension.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Pension extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String owner;

    @CsExpose
    private String alias;

    @CsExpose
    private Date signingDate;

    @CsExpose
    private Date validFrom;

    @CsExpose
    private Date validTo;

    @CsExpose
    private String agreementNumber;

    private PensionStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private ProductAccount productAccount;

    @CsExpose
    private String product;

    @CsExpose
    private String productI18N;

    private PensionSubtype subtype;

    @CsExpose
    @CsSerializedName("subtype")
    private String subtypeRaw;

    @CsExpose
    private String birthNumber;

    @CsExpose
    private Amount paidBenefits;

    @CsExpose
    private Strategy strategy;

    @CsExpose
    private PensionAgreed pensionAgreed;

    @CsExpose
    private SavingTime savingTime;

    @CsExpose
    private Contribution contribution;

    @CsExpose
    private Supplementary supplementary;

    @CsExpose
    private List<Beneficiary> beneficiaries;

    @CsExpose
    private List<String> flags;

    /**
     * Product unique identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Name of the contract owner.
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    public String getAlias() {
        return alias;
    }

    /**
     * Date when contract was signed.
     *
     * @return the signing date
     */
    public Date getSigningDate() {
        return signingDate;
    }

    /**
     * First day of contract validity.
     *
     * @return the valid from
     */
    public Date getValidFrom() {
        return validFrom;
    }

    /**
     * Last day of contract validity.
     *
     * @return the valid to
     */
    public Date getValidTo() {
        return validTo;
    }

    /**
     * Pension contract number.
     *
     * @return the agreement number
     */
    public String getAgreementNumber() {
        return agreementNumber;
    }

    /**
     * Contract status. Possible values: ACTIVE, TERMINATED, PENSION_PAYMENT, INTERRUPTED,
     * PAYMENTS_SUSPENDED, PAYMENTS_DEFFERED, SETTLED, REPEALED, NEGOTIATED.
     *
     * @return the status
     */
    public PensionStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(PensionStatus.class, statusRaw);
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
     * Product account.
     *
     * @return the product account
     */
    public ProductAccount getProductAccount() {
        return productAccount;
    }

    /**
     * Identification of the product type.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Localized product name.
     *
     * @return the product
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * Identification of the product group. Possible values are SUPPLEMENTARY_INSURANCE,
     * PENSION_SAVINGS and SUPPLEMENTARY_SAVINGS.
     *
     * @return the subtype
     */
    public PensionSubtype getSubtype() {
        if (subtype == null && subtypeRaw != null)
            subtype = EnumUtils.translateToEnum(PensionSubtype.class, subtypeRaw);
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
     * Birth number of the product owner.
     *
     * @return the birth number
     */
    public String getBirthNumber() {
        return birthNumber;
    }

    /**
     * Amount of already paid benefits.
     *
     * @return the paid benefits
     */
    public Amount getPaidBenefits() {
        return paidBenefits;
    }

    /**
     * The pension strategy
     *
     * @return the strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * The agreed pension
     *
     * @return the pension agreed
     */
    public PensionAgreed getPensionAgreed() {
        return pensionAgreed;
    }

    /**
     * The saving time.
     *
     * @return the saving time
     */
    public SavingTime getSavingTime() {
        return savingTime;
    }

    /**
     * Employer contribution
     *
     * @return the contribution
     */
    public Contribution getContribution() {
        return contribution;
    }

    /**
     * Supplementary info
     *
     * @return the supplementary
     */
    public Supplementary getSupplementary() {
        return supplementary;
    }

    /**
     * List of beneficiaries.
     *
     * @return the beneficiaries
     */
    public List<Beneficiary> getBeneficiaries() {
        return beneficiaries;
    }

    /**
     * Array of flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Convenience method for fetching Pensions detail
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Pension> callback) {
        if (resource instanceof PensionsContractResource)
            ((PensionsContractResource) resource).get(callback);
    }

    /**
     * Convenience method for updating Pension
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(PensionUpdateRequest request, CallbackWebApi<PensionUpdateResponse> callback) {
        if (resource instanceof PensionsContractResource)
            ((PensionsContractResource) resource).update(request, callback);
    }

    /**
     * Convenience getter for getting Pensions transactions resource
     *
     * @return the transactions resource
     */
    public TransactionsResource getTransactionsResource() {
        if (resource instanceof PensionsContractResource)
            return ((PensionsContractResource) resource).getTransactionsResource();
        return null;
    }
}

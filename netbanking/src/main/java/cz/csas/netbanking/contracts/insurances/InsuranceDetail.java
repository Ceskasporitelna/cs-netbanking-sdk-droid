package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Insurance detail.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceDetail extends WebApiEntity {

    @CsExpose
    private String id;

    private InsuranceType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String product;

    @CsExpose
    private String productI18N;

    @CsExpose
    private String alias;

    @CsExpose
    private String insurancePolicyHolder;

    @CsExpose
    private String policyNumber;

    private InsuranceStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private LifeDetail life;

    @CsExpose
    private String description;

    /**
     * Contract number.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Product Type of insurance. ENUM values: LIFE (CSAS supports only this value)
     *
     * @return the type
     */
    public InsuranceType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(InsuranceType.class, typeRaw);
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
     * Code of the sVersicherung product.
     *
     * @return the product
     */
    public String getProduct() {
        return product;
    }

    /**
     * Name of the sVersicherung product (localised).
     *
     * @return the product i 18 n
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * User-specific alias of the contract. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * The primary holder of the specific insurance contract.
     *
     * @return the insurance policy holder
     */
    public String getInsurancePolicyHolder() {
        return insurancePolicyHolder;
    }

    /**
     * Policy number
     *
     * @return the policy number
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Status: ACTIVE or CLOSED
     *
     * @return the status
     */
    public InsuranceStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(InsuranceStatus.class, statusRaw);
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
     * Get life insurance info
     *
     * @return the life
     */
    public LifeDetail getLife() {
        return life;
    }

    /**
     * Additional description of insurance product, additional charges, index applied to insurance contract
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}

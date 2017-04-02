package cz.csas.netbanking.contracts.pensions;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Address;

/**
 * The type Beneficiary.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Beneficiary extends WebApiEntity {

    @CsExpose
    private String name;

    @CsExpose
    private Date birthDate;

    @CsExpose
    private String birthNumber;

    @CsExpose
    private Address address;

    @CsExpose
    private Double share;

    private Entitlement entitlement;

    @CsExpose
    @CsSerializedName("entitlement")
    private String entitlementRaw;

    /**
     * Beneficiary name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Beneficiary birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Beneficiary birth number.
     *
     * @return the birth number
     */
    public String getBirthNumber() {
        return birthNumber;
    }

    /**
     * Address where card should be sent.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Beneficiary share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     *
     * @return the share
     */
    public Double getShare() {
        return share;
    }

    /**
     * Entitlement type. Possible values TAKEOVER.
     *
     * @return the entitlement
     */
    public Entitlement getEntitlement() {
        if (entitlement == null && entitlementRaw != null)
            entitlement = EnumUtils.translateToEnum(Entitlement.class, entitlementRaw);
        return entitlement;
    }

    /**
     * Get entitlement raw.
     *
     * @return the entitlement raw
     */
    public String getEntitlementRaw() {
        return entitlementRaw;
    }
}

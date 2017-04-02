package cz.csas.netbanking.plugins;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Plugin.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Plugin extends WebApiEntity {

    @CsExpose
    private String productCode;

    @CsExpose
    private String name;

    @CsExpose
    private List<StandardFee> standardFees;

    @CsExpose
    private AccountNumber settlementAccount;

    @CsExpose
    private Date validUntil;

    @CsExpose
    private Date dateOfActivation;

    @CsExpose
    private List<String> flags;

    /**
     * Plugin unique identifier.
     *
     * @return the product code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Localized name of the plugin.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets standard fees.
     *
     * @return the standard fees
     */
    public List<StandardFee> getStandardFees() {
        return standardFees;
    }

    /**
     * User settlement account for charging fees.
     *
     * @return the settlement account
     */
    public AccountNumber getSettlementAccount() {
        return settlementAccount;
    }

    /**
     * Date until plugin is valid.
     *
     * @return the valid until
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * Date of activation of plugin for user.
     *
     * @return the date of activation
     */
    public Date getDateOfActivation() {
        return dateOfActivation;
    }

    /**
     * Array of optional flag values.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

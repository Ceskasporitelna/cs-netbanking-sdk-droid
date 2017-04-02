package cz.csas.netbanking.plugins;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Plugin update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class PluginUpdateRequest extends WebApiRequest {

    @CsExpose
    private String productCode;

    @CsExpose
    private AccountNumber settlementAccount;

    @CsExpose
    private List<String> flags;

    public PluginUpdateRequest(String productCode, AccountNumber settlementAccount, List<String> flags) {
        this.productCode = productCode;
        this.settlementAccount = settlementAccount;
        this.flags = flags;
    }

    /**
     * Plugin unique identifier.
     *
     * @return the product code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Plugin unique identifier.
     *
     * @param productCode the product code
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
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
     * User settlement account for charging fees.
     *
     * @param settlementAccount the settlement account
     */
    public void setSettlementAccount(AccountNumber settlementAccount) {
        this.settlementAccount = settlementAccount;
    }

    /**
     * Array of optional flag values.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Array of optional flag values.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Change account settings request is used to update alias for account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class ChangeAccountSettingsRequest extends WebApiRequest {

    @CsExpose
    private String alias;

    /**
     * Instantiates a new Change account settings request.
     *
     * @param alias the alias
     */
    public ChangeAccountSettingsRequest(String alias) {
        this.alias = alias;
    }

    /**
     * Get alias of the account in request.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Set alias in request.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

}

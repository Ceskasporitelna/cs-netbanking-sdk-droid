package cz.csas.netbanking.securities;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Security update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class SecurityUpdateRequest extends WebApiRequest {

    @CsExpose
    private String alias;

    public SecurityUpdateRequest(String alias) {
        this.alias = alias;
    }

    /**
     * Alias for security portfolio. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias for security portfolio. Max. 50 characters.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}

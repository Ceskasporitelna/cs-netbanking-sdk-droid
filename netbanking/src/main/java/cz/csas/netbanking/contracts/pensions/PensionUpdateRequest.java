package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Pension update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class PensionUpdateRequest extends WebApiRequest {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    public PensionUpdateRequest(String alias) {
        this.id = id;
        this.alias = alias;
    }

    /**
     * Pension contract id
     * @param id the id
     */
    void setId(String id) {
        this.id = id;
    }

    /**
     * User defined account name. Max. 50 characters.
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * User defined account name. Max. 50 characters.
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}

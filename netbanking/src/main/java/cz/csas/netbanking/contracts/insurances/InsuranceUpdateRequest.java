package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Insurance update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceUpdateRequest extends WebApiRequest {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    public InsuranceUpdateRequest(String alias) {
        this.id = id;
        this.alias = alias;
    }

    /**
     * Insurance contract id
     *
     * @param id the id
     */
    void setId(String id) {
        this.id = id;
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
     * User-specific alias of the contract. Max. 50 characters.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}

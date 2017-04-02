package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Update buildings contract request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContractUpdateRequest extends WebApiRequest {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    /**
     * Instantiates a new Update buildings contract request.
     *
     * @param alias user-specific alias of the contract. Max. 50 characters.
     */
    public BuildingsContractUpdateRequest(String alias) {
        this.alias = alias;
    }

    /**
     * Set the contract id.
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
     * Sets the user-specific alias of the contract. Max. 50 characters.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }
}

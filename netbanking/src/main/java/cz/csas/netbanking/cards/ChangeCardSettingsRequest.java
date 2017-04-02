package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Change card settings request is used to change card settings.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class ChangeCardSettingsRequest extends WebApiRequest {

    @CsExpose
    private String alias;

    /**
     * Instantiates a new Change card settings request.
     *
     * @param alias the alias
     */
    public ChangeCardSettingsRequest(String alias) {
        this.alias = alias;
    }

    /**
     * Set alias of the card.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Get alias of the card.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

}

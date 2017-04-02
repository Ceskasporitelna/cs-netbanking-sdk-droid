package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Card limits list response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardLimitsListResponse extends ListResponse<CardLimit>{

    @CsExpose
    @CsSerializedName(value = "limits", alternate = "items")
    private List<CardLimit> limits;

    @Override
    protected List<CardLimit> getItems() {
        return limits;
    }

    /**
     * Get list of card limits.
     * Convenience method for {@link #getItems()}
     *
     * @return the limits
     */
    public List<CardLimit> getLimits() {
        return limits;
    }
}

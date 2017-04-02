package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Cards list response provides list of Cards.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardsListResponse extends PaginatedListResponse<Card,CardsListResponse> {

    @CsExpose
    @CsSerializedName(value = "cards", alternate = "items")
    private List<Card> cards;

    @Override
    protected List<Card> getItems() {
        return cards;
    }

    /**
     * Get list of cards.
     * Convenience method for {@link #getItems()}
     *
     * @return the cards list
     */
    public List<Card> getCards() {
        return cards;
    }
}

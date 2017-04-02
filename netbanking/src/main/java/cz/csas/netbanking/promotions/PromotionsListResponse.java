package cz.csas.netbanking.promotions;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.ListOfPrimitivesResponse;

/**
 * The type Promotions list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class PromotionsListResponse extends ListOfPrimitivesResponse<Promotion> {

    @CsExpose
    private List<Promotion> promotions;

    @Override
    protected List<Promotion> getItems() {
        return promotions;
    }

    /**
     * Get promotions.
     *
     * @return the promotions
     */
    public List<Promotion> getPromotions() {
        return promotions;
    }
}

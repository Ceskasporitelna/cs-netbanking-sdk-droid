package cz.csas.netbanking.promotions;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Create promotion response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class PromotionActionCreateResponse extends WebApiEntity {

    /**
     * The Info items.
     */
    @CsExpose
    List<InfoItem> infoItems;

    /**
     * Get info items.
     *
     * @return the info items
     */
    public List<InfoItem> getInfoItems() {
        return infoItems;
    }
}

package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Standing orders list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class StandingOrdersListResponse extends PaginatedListResponse<StandingOrder, StandingOrdersListResponse> {

    @CsExpose
    @CsSerializedName(value = "standingOrders", alternate = "items")
    private List<StandingOrder> standingOrders;

    @Override
    protected List<StandingOrder> getItems() {
        return standingOrders;
    }

    public List<StandingOrder> getStandingOrders() {
        return standingOrders;
    }
}

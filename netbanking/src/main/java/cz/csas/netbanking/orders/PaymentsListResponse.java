package cz.csas.netbanking.orders;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * Orders list response provides list od payments.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentsListResponse extends PaginatedListResponse<Payment, PaymentsListResponse> {

    @CsExpose
    //TODO: change annotation in V4 to @CsSerializedName(value = "payments", alternate = "items")
    @CsSerializedName(value = "order", alternate = "items")
    private List<Payment> payments;

    @Override
    protected List<Payment> getItems() {
        return payments;
    }

    /**
     * Get list of payments.
     * Convenience method for {@link #getItems()}
     *
     * @return the payments
     */
    public List<Payment> getPayments() {
        return payments;
    }
}

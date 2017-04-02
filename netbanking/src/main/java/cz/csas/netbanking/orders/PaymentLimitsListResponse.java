package cz.csas.netbanking.orders;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Payment limits list response provides payment limits list.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentLimitsListResponse extends ListResponse<PaymentLimit> {

    @CsExpose
    @CsSerializedName(value = "remainingLimits", alternate = "items")
    private List<PaymentLimit> paymentLimits;

    @Override
    protected List<PaymentLimit> getItems() {
        return paymentLimits;
    }

    /**
     * Get payment limits list.
     * Convenience method for {@link #getItems()}
     *
     * @return the payment limits
     */
    public List<PaymentLimit> getPaymentLimits() {
        return paymentLimits;
    }
}

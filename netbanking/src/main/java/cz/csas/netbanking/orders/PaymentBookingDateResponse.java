package cz.csas.netbanking.orders;

import java.util.Date;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Payment order booking date response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class PaymentBookingDateResponse extends WebApiEntity {

    @CsExpose
    private String bookingDate;

    /**
     * Get booking date value for provided account ID and payment.
     *
     * @return the booking date
     */
    public Date getBookingDate() {
        return TimeUtils.getISO8601Date(bookingDate);
    }
}

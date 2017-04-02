package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Service list response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class ReservationsListResponse extends PaginatedListResponse<Reservation,
        ReservationsListResponse> {

    @CsExpose
    @CsSerializedName(value = "reservations", alternate = "items")
    private List<Reservation> reservations;

    @Override
    protected List<Reservation> getItems() {
        return reservations;
    }

    /**
     * Get list of reservations.
     * Convenience method for {@link #getItems()}
     *
     * @return the reservations
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
}

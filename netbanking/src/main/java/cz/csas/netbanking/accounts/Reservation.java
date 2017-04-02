package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Reservation provides information about reservation.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Reservation extends WebApiEntity {

    @CsExpose
    private ReservationType type;

    private ReservationStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private String creationDate;

    @CsExpose
    private String expirationDate;

    @CsExpose
    private String merchantName;

    @CsExpose
    @CsSerializedName(value = "cz-merchantAddress")
    private String czMerchantAddress;

    @CsExpose
    private String description;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Amount amountSender;

    /**
     * Get type of reservation. Possible values are CASH_WITHDRAWAL, PAYMENT, CARD_PAYMENT, OTHER.
     *
     * @return the type
     */
    public ReservationType getType() {
        return type;
    }

    /**
     * Get reservation status. Possible values are RESERVED, CANCELLED, EXPIRED. Currently only
     * reservations with status RESERVED are supported.
     *
     * @return the status
     */
    public ReservationStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(ReservationStatus.class, statusRaw);
        return status;
    }

    /**
     * Get status raw.
     *
     * @return the status raw
     */
    public String getStatusRaw() {
        return statusRaw;
    }

    /**
     * Get date and time when the reservation was created.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return TimeUtils.getISO8601Date(creationDate);
    }

    /**
     * Get reservation expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return TimeUtils.getISO8601Date(expirationDate);
    }

    /**
     * Get merchant Name / ATM.
     *
     * @return the merchant name
     */
    public String getMerchantName() {
        return merchantName;
    }

    /**
     * Get merchant address.
     *
     * @return the merchant address
     */
    public String getCzMerchantAddress() {
        return czMerchantAddress;
    }

    /**
     * Get reservation description, additional info.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get the amount of reservation in account's currency
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get the amount of the reservation in transaction currency
     *
     * @return the amount sender
     */
    public Amount getAmountSender() {
        return amountSender;
    }
}

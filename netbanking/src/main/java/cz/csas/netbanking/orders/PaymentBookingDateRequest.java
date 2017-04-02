package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.AccountNumber;

/**
 * The type Payment order booking date request.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class PaymentBookingDateRequest extends WebApiRequest {


    private String accountId;

    @CsExpose
    private AccountNumber receiver;

    private PaymentOrderPriority priority;

    @CsExpose
    @CsSerializedName("priority")
    private String priorityRaw;

    /**
     * Instantiates a new Payment order booking date request.
     *
     * @param accountId the account id
     * @param receiver  the receiver
     * @param priority  the priority
     */
    public PaymentBookingDateRequest(String accountId, AccountNumber receiver, String priority) {
        this.accountId = accountId;
        this.receiver = receiver;
        this.priorityRaw = priority;
    }

    /**
     * Instantiates a new Payment booking date request.
     *
     * @param accountId the account id
     * @param receiver  the receiver
     * @param priority  the priority
     */
    public PaymentBookingDateRequest(String accountId, AccountNumber receiver, PaymentOrderPriority priority) {
        this.accountId = accountId;
        this.receiver = receiver;
        this.priorityRaw = priority.getValue();
    }

    /**
     * Get account ID, not exposed but sent as a query param.
     *
     * @return the account id
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Set account ID, not exposed but sent as a query param.
     *
     * @param accountId the account id
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    /**
     * Get receiver's account number.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Set receiver's account number.
     *
     * @param receiver the receiver
     */
    public void setReceiver(AccountNumber receiver) {
        this.receiver = receiver;
    }

    /**
     * Get payment order priority selected by user,
     * ENUM values: URGENT (for express payments), STANDARD.
     *
     * @return the priority
     */
    public PaymentOrderPriority getPriority() {
        if (priority == null && priorityRaw != null)
            priority = EnumUtils.translateToEnum(PaymentOrderPriority.class, priorityRaw);
        return priority;
    }

    /**
     * Get priority raw.
     *
     * @return the priority raw
     */
    public String getPriorityRaw() {
        return priorityRaw;
    }

    /**
     * Set payment order priority selected by user,
     * ENUM values: URGENT (for express payments), STANDARD.
     *
     * @param priority the priority
     */
    public void setPriority(PaymentOrderPriority priority) {
        this.priorityRaw = priority.getValue();
    }

    /**
     * Set payment order priority selected by user,
     * ENUM values: URGENT (for express payments), STANDARD.
     *
     * @param priority the priority
     */
    public void setPriority(String priority) {
        this.priorityRaw = priority;
    }
}

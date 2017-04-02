package cz.csas.netbanking.orders;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;

/**
 * The type Payment provides information about payment.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl()
public class Payment extends WebApiEntity implements Signable {

    @CsExpose
    private String id;

    @CsExpose
    private String referenceId;

    private PaymentCategory orderCategory;

    @CsExpose
    @CsSerializedName("orderCategory")
    private String orderCategoryRaw;

    private PaymentOrderType orderType;

    @CsExpose
    @CsSerializedName("orderType")
    private String orderTypeRaw;

    @CsExpose
    private String senderName;

    @CsExpose
    private AccountNumber sender;

    @CsExpose
    private String receiverName;

    @CsExpose
    private AccountNumber receiver;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Symbols symbols;

    @CsExpose
    private Info additionalInfo;

    @CsExpose
    private String senderReference;

    @CsExpose
    private String executionDate;

    @CsExpose
    private String modificationDate;

    @CsExpose
    private Date transferDate;

    @CsExpose
    private String expirationDate;

    @CsExpose
    @CsSerializedName(value = "cz-orderingDate")
    private String czOrderingDate;

    private PaymentState state;

    @CsExpose
    @CsSerializedName("state")
    private String stateRaw;

    private PaymentStateDetail stateDetail;

    @CsExpose
    @CsSerializedName("stateDetail")
    private String stateDetailRaw;

    @CsExpose
    private Boolean stateOk;

    @CsExpose
    @CsSerializedName(value = "cz-description")
    private String czDescription;

    private ApplicationId applicationId;

    @CsExpose
    @CsSerializedName("applicationId")
    private String applicationIdRaw;

    private ChannelId channelId;

    @CsExpose
    @CsSerializedName("channelId")
    private String channelIdRaw;

    @CsExpose
    private List<String> receiverAddress;

    @CsExpose
    private List<String> flags;

    private SigningObject signingObject;

    /**
     * Get internal identifier of payment order. Note that after signing of the order the id could
     * change.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get transaction reference ID provided by BE when payment order was executed.
     *
     * @return the reference id
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Get payment order category determines whether payment is domestic, SEPA, international or
     * inside the bank (domestic, but could be different processing) or between accounts of the
     * same user (domestic, but with better fee policy).
     * Possible values: DOMESTIC, OWN_TRANSFER, SEPA, INTERNATIONAL.
     *
     * @return the payment category
     */
    public PaymentCategory getOrderCategory() {
        if (orderCategory == null && orderCategoryRaw != null)
            orderCategory = EnumUtils.translateToEnum(PaymentCategory.class, orderCategoryRaw);
        return orderCategory;
    }

    /**
     * Get order category raw.
     *
     * @return the order category raw
     */
    public String getOrderCategoryRaw() {
        return orderCategoryRaw;
    }

    /**
     * Get payment order type (outgoing payment, outgoing direct debit, incoming direct debit)
     * determines further transaction processing in BE.
     * Values: PAYMENT_OUT, DIRECT_DEBIT_IN
     *
     * @return the payment order type
     */
    public PaymentOrderType getOrderType() {
        if (orderType == null && orderTypeRaw != null)
            orderType = EnumUtils.translateToEnum(PaymentOrderType.class, orderTypeRaw);
        return orderType;
    }

    /**
     * Get order type raw.
     *
     * @return the order type raw
     */
    public String getOrderTypeRaw() {
        return orderTypeRaw;
    }

    /**
     * Get sender name.
     *
     * @return the sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Get sender account number.
     *
     * @return the sender
     */
    public AccountNumber getSender() {
        return sender;
    }

    /**
     * Get receiver name.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Get receiver IBAN in case of international payments.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Get payment amount.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get information about the symbols
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * Get message for payee set during payment order creation.
     * It is used to identify transaction on receiver side. Array of texts 4x35.
     *
     * @return the additional info
     */
    public Info getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Get message for me set during payment order creation.
     *
     * @return the sender reference
     */
    public String getSenderReference() {
        return senderReference;
    }

    /**
     * Get datetime when payment order was created/updated (the last time)
     * by user (read-only field is automatically setup/changed by BE system based on POST/PUT request).
     *
     * @return the execution date
     */
    public Date getExecutionDate() {
        return TimeUtils.getISO8601Date(executionDate);
    }

    /**
     * Get modification date indicates the last update of payment order done by user or BE
     * system (read-only field provided by BE).
     *
     * @return the modification date
     */
    public Date getModificationDate() {
        return TimeUtils.getISO8601Date(modificationDate);
    }

    /**
     * Get payment transfer date.
     *
     * @return the transfer date
     */
    public Date getTransferDate() {
        return transferDate;
    }

    /**
     * Get datetime till when payment order will be repeated on BE in the case of insufficient funds on account.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return TimeUtils.getISO8601Date(expirationDate);
    }

    /**
     * Get date and time which should be used for default ordering of the payment orders for display.
     *
     * @return the ordering date
     */
    public Date getCzOrderingDate() {
        return TimeUtils.getISO8601Date(czOrderingDate);
    }

    /**
     * Get status of the payment order (details above), State of payment order presented to user on FE).
     * Possible values: OPEN, SPOOLED, CANCELLED, CLOSED and DELETED.
     *
     * @return the state
     */
    public PaymentState getState() {
        if (state == null && stateRaw != null)
            state = EnumUtils.translateToEnum(PaymentState.class, stateRaw);
        return state;
    }

    /**
     * Get state raw.
     *
     * @return the state raw
     */
    public String getStateRaw() {
        return stateRaw;
    }

    /**
     * Get state detail of payment order provided based on BE technical states.
     *
     * @return the state detail
     */
    public PaymentStateDetail getStateDetail() {
        if (stateDetail == null && stateDetailRaw != null)
            stateDetail = EnumUtils.translateToEnum(PaymentStateDetail.class, stateDetailRaw);
        return stateDetail;
    }

    /**
     * Get state detail raw.
     *
     * @return the state detail raw
     */
    public String getStateDetailRaw() {
        return stateDetailRaw;
    }

    /**
     * Indicator whether state (stateDetail value) of payment order is OK from user point of view.
     * For mapping between stateDetail and stateOk indicator values see table below.
     *
     * @return true if state ok
     */
    public Boolean getStateOk() {
        return stateOk;
    }

    /**
     * Get description of payment order, transaction type
     *
     * @return the description
     */
    public String getCzDescription() {
        return czDescription;
    }

    /**
     * Get ID of the application via which this payment order was entered/modified the last time.
     * Possible values: GEORGE, ATM_PAYMENT, ATM_OTHER, GSM, BRANCH_FE, POST_OFFICE,
     * INTERNET_BANKING, TELEPHONE_BANKER, COLLECTION_BOX, VIDEO_BANKER and UNKNOWN.
     *
     * @return the application id
     */
    public ApplicationId getApplicationId() {
        if (applicationId == null && applicationIdRaw != null)
            applicationId = EnumUtils.translateToEnum(ApplicationId.class, applicationIdRaw);
        return applicationId;
    }

    /**
     * Get application id raw.
     *
     * @return the application id raw
     */
    public String getApplicationIdRaw() {
        return applicationIdRaw;
    }

    /**
     * Get ID of the channel via which this payment order was entered/modified the last time.
     * Possible values: NET_BANKING, ATM, MOBILE_BANKING, ATM, BRANCH, POST_OFFICE, CALL_CENTRE,
     * VIDEO_BANKING and UNKNOWN.
     *
     * @return the channel id
     */
    public ChannelId getChannelId() {
        if (channelId == null && channelIdRaw != null)
            channelId = EnumUtils.translateToEnum(ChannelId.class, channelIdRaw);
        return channelId;
    }

    /**
     * Get channel id raw.
     *
     * @return the channel id raw
     */
    public String getChannelIdRaw() {
        return channelIdRaw;
    }

    /**
     * Get receiver's address. Array of 3x text fields with max 35 characters each.
     * First text - Street, Second text - ZIP code and City, Third text - Country code, name.
     * (Used only for International payments)
     *
     * @return the receiver address
     */
    public List<String> getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * Get array of optional Flag values depends on Payment order category, type.
     * Possible flags:
     * Flag	        Description
     * <p>
     * urgent	    Flag indicating urgent payment order (in SEPA, SWIFT and maybe also in local
     * bank clearing systems) requested by client.
     * editable	    Flag indicating if payment order can be edited by client.
     * deletable	Flag indicating if payment order can be deleted by client.
     * cancelable	Flag indicating if future dated (already signed) payment order can be canceled by client
     * redoable	    Flag indicating if payment order can be re-done
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Convenience method for retrieving payment's detail.
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Payment> callback) {
        if (resource instanceof PaymentResource)
            ((PaymentResource) resource).get(callback);
    }

    /**
     * Convenience method for deleting payment.
     *
     * @param callback the callback
     */
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        if (resource instanceof PaymentResource)
            ((PaymentResource) resource).delete(callback);
    }

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

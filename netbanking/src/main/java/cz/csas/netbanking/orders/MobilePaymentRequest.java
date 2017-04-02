package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.Amount;

/**
 * The type Mobile payment request.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class MobilePaymentRequest extends WebApiRequest {

    private MobilePaymentType paymentType;

    @CsExpose
    @CsSerializedName("paymentType")
    private String paymentTypeRaw;

    @CsExpose
    private String phoneNumber;

    @CsExpose
    private MobilePaymentSender sender;

    @CsExpose
    private Amount amount;

    @CsExpose
    private String invoiceNumber;

    @CsExpose
    private String confirmationPhoneNumber;

    private MobilePaymentRequest(Builder builder) {
        paymentTypeRaw = builder.paymentType;
        phoneNumber = builder.phoneNumber;
        sender = builder.sender;
        amount = builder.amount;
        invoiceNumber = builder.invoiceNumber;
        confirmationPhoneNumber = builder.confirmationPhoneNumber;
    }

    /**
     * Get type of mobile payment depending on provider of mobile services.
     * Possible values: TOP_UP (for all operators) and INVOICE, VODAFONE_PAYMENT, MOBILE_DEPOSIT
     * (for Vodafone).
     *
     * @return the payment type
     */
    public MobilePaymentType getPaymentType() {
        if (paymentType == null && paymentTypeRaw != null)
            paymentType = EnumUtils.translateToEnum(MobilePaymentType.class, paymentTypeRaw);
        return paymentType;
    }

    /**
     * Get payment type raw.
     *
     * @return the payment type raw
     */
    public String getPaymentTypeRaw() {
        return paymentTypeRaw;
    }

    /**
     * Get phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get mobile payment sender.
     *
     * @return the sender
     */
    public MobilePaymentSender getSender() {
        return sender;
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
     * Get invoice number used as identifier of mobile payment on mobile service provider side
     * (only for paymentType: INVOICE).
     *
     * @return the invoice number
     */
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    /**
     * Get phone number used for sending of confirmation of mobile payment execution. Not available for
     * paymentType: INVOICE.
     *
     * @return the confirmation phone number
     */
    public String getConfirmationPhoneNumber() {
        return confirmationPhoneNumber;
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private String paymentType;
        private String phoneNumber;
        private MobilePaymentSender sender;
        private Amount amount;
        private String invoiceNumber;
        private String confirmationPhoneNumber;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Set type of mobile payment depending on provider of mobile services.
         * Possible values: TOP_UP (for all operators) and INVOICE, VODAFONE_PAYMENT, MOBILE_DEPOSIT
         * (for Vodafone).
         *
         * @param val the val
         * @return the payment type
         */
        public Builder setPaymentType(MobilePaymentType val) {
            paymentType = val.getValue();
            return this;
        }

        /**
         * Set type of mobile payment depending on provider of mobile services.
         * Possible values: TOP_UP (for all operators) and INVOICE, VODAFONE_PAYMENT, MOBILE_DEPOSIT
         * (for Vodafone).
         *
         * @param val the val
         * @return the payment type
         */
        public Builder setPaymentType(String val) {
            paymentType = val;
            return this;
        }

        /**
         * Set phone number.
         *
         * @param val the val
         * @return the phone number
         */
        public Builder setPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        /**
         * Set mobile payment sender.
         *
         * @param val the val
         * @return the sender
         */
        public Builder setSender(MobilePaymentSender val) {
            sender = val;
            return this;
        }

        /**
         * Set payment amount.
         *
         * @param val the val
         * @return the amount
         */
        public Builder setAmount(Amount val) {
            amount = val;
            return this;
        }

        /**
         * Set invoice number used as identifier of mobile payment on mobile service provider side
         * (only for paymentType: INVOICE).
         *
         * @param val the val
         * @return the invoice number
         */
        public Builder setInvoiceNumber(String val) {
            invoiceNumber = val;
            return this;
        }

        /**
         * Set phone number used for sending of confirmation of mobile payment execution. Not available for
         * paymentType: INVOICE.
         *
         * @param val the val
         * @return the confirmation phone number
         */
        public Builder setConfirmationPhoneNumber(String val) {
            confirmationPhoneNumber = val;
            return this;
        }

        /**
         * Build mobile payment request.
         *
         * @return the mobile payment request
         */
        public MobilePaymentRequest build() {
            return new MobilePaymentRequest(this);
        }
    }
}

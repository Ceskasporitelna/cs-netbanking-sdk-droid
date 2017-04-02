package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.netbanking.Amount;

/**
 * The type Mobile payment response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl()
public class MobilePaymentResponse extends WebApiEntity implements Signable {

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

    private SigningObject signingObject;

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

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

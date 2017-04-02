package cz.csas.netbanking.contracts.insurances;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Indemnity.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Indemnity {

    @CsExpose
    private Date paymentDate;

    @CsExpose
    private String transferMethod;

    @CsExpose
    private String receiverName;

    @CsExpose
    private Amount amount;

    /**
     * Date of the payment was paid out.
     *
     * @return the payment date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * Method of the transfer.
     *
     * @return the transfer method
     */
    public String getTransferMethod() {
        return transferMethod;
    }

    /**
     * Receiver name.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Paid indemnity value.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }
}

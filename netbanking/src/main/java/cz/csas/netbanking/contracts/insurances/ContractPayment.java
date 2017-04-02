package cz.csas.netbanking.contracts.insurances;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Contract payment.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractPayment extends WebApiEntity {

    @CsExpose
    private String id;

    private PaymentType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private Date transactionDate;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Amount restToPay;

    @CsExpose
    private Amount instruction;

    @CsExpose
    private Amount employerContribution;

    @CsExpose
    private Date instructionFrom;

    @CsExpose
    private Date instructionTo;

    @CsExpose
    private List<String> flags;

    /**
     * Payment identifier. Unique for current insurance.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Type of the payment. Possible values are ORDINARY, ONETIME, EXTRAORDINARY, FUTURE, OVERDUE, WITHDRAWAL, PARTIALLY_PAID, UNKNOWN.
     *
     * @return the type
     */
    public PaymentType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(PaymentType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Payment date.
     *
     * @return the transaction date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }

    /**
     * Payment amount. Amount which was received by insurance company.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Rest which should be paid if payment instruction wasn't fully paid by this payment.
     *
     * @return the rest to pay
     */
    public Amount getRestToPay() {
        return restToPay;
    }

    /**
     * Payment instruction amount. Amount which should be paid for particular period.
     *
     * @return the instruction
     */
    public Amount getInstruction() {
        return instruction;
    }

    /**
     * Amount paid by employer (as benefit). It is included in the payment amount.
     *
     * @return the employer contribution
     */
    public Amount getEmployerContribution() {
        return employerContribution;
    }

    /**
     * Start date of the period for which payment instruction was created.
     *
     * @return the instruction from
     */
    public Date getInstructionFrom() {
        return instructionFrom;
    }

    /**
     * End date of the period for which payment instruction was created.
     *
     * @return the instruction to
     */
    public Date getInstructionTo() {
        return instructionTo;
    }

    /**
     * Array of flags for funds.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

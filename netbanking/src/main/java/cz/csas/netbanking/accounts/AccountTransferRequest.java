package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.Amount;

/**
 * The type Account transfer.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class AccountTransferRequest extends WebApiRequest {

    private AccountsTransferType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String transferDate;

    @CsExpose
    private String recipientNote;

    @CsExpose
    private Amount amount;

    /**
     * Instantiates a new Account transfer request.
     *
     * @param type          the type
     * @param transferDate  the transfer date
     * @param recipientNote the recipient note
     * @param amount        the amount
     */
    public AccountTransferRequest(AccountsTransferType type, Date transferDate,
                                  String recipientNote, Amount amount) {
        this.typeRaw = type.getValue();
        this.transferDate = TimeUtils.getPlainDateString(transferDate);
        this.recipientNote = recipientNote;
        this.amount = amount;
    }

    public AccountTransferRequest(String type, Date transferDate,
                                  String recipientNote, Amount amount) {
        this.typeRaw = type;
        this.transferDate = TimeUtils.getPlainDateString(transferDate);
        this.recipientNote = recipientNote;
        this.amount = amount;
    }


    /**
     * Set type of the transfer. Currently only REVOLVING_LOAN_DISBURSEMENT is supported..
     *
     * @param type the type
     */
    public void setType(AccountsTransferType type) {
        this.typeRaw = type.getValue();
    }

    /**
     * Set type of the transfer. Currently only REVOLVING_LOAN_DISBURSEMENT is supported..
     *
     * @param type the type
     */
    public void setType(String type) {
        this.typeRaw = type;
    }

    /**
     * Set payment transfer date.
     *
     * @param transferDate the transfer date
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = TimeUtils.getPlainDateString(transferDate);
    }

    /**
     * Set note for the recipient.
     *
     * @param recipientNote the recipient note
     */
    public void setRecipientNote(String recipientNote) {
        this.recipientNote = recipientNote;
    }

    /**
     * Set amount which should be transfered.
     *
     * @param amount the amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * Get type of the transfer. Currently only REVOLVING_LOAN_DISBURSEMENT is supported.
     *
     * @return the transfer type
     */
    public AccountsTransferType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(AccountsTransferType.class, typeRaw);
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
     * Get payment transfer date.
     *
     * @return the transfer date
     */
    public Date getTransferDate() {
        return TimeUtils.getPlainDate(transferDate);
    }

    /**
     * Get note for the recipient.
     *
     * @return the note
     */
    public String getRecipientNote() {
        return recipientNote;
    }

    /**
     * Get amount which should be transfered.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }
}

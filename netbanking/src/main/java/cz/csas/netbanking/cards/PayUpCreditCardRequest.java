package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Pay up credit card request.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class PayUpCreditCardRequest {

    private CardTransferType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;
    @CsExpose
    private Sender sender;

    @CsExpose
    private Amount amount;

    /**
     * Instantiates a new Pay up credit card request.
     *
     * @param type   the type
     * @param sender the sender
     * @param amount the amount
     */
    public PayUpCreditCardRequest(CardTransferType type, Sender sender, Amount amount) {
        this.typeRaw = type.getValue();
        this.sender = sender;
        this.amount = amount;
    }

    /**
     * Instantiates a new Pay up credit card request.
     *
     * @param type    the type
     * @param typeRaw the type raw
     * @param sender  the sender
     * @param amount  the amount
     */
    public PayUpCreditCardRequest(String type, String typeRaw, Sender sender, Amount amount) {
        this.typeRaw = type;
        this.typeRaw = typeRaw;
        this.sender = sender;
        this.amount = amount;
    }

    /**
     * Set type of the transfer. Currently only DEBT_REPAYMENT is supported.
     *
     * @param type the type
     */
    public void setType(CardTransferType type) {
        this.typeRaw = type.getValue();
    }

    /**
     * Set type of the transfer. Currently only DEBT_REPAYMENT is supported.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.typeRaw = type;
    }

    /**
     * Set information about the sender.
     *
     * @param sender the sender
     */
    public void setSender(Sender sender) {
        this.sender = sender;
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
     * Get type of the transfer. Currently only DEBT_REPAYMENT is supported.
     *
     * @return the type
     */
    public CardTransferType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(CardTransferType.class, typeRaw);
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
     * Get information about the sender.
     *
     * @return the sender
     */
    public Sender getSender() {
        return sender;
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

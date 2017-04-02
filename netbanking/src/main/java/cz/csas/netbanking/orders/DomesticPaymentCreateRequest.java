package cz.csas.netbanking.orders;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;

/**
 * The type Domestic order request.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class DomesticPaymentCreateRequest extends WebApiRequest {

    @CsExpose
    protected String senderName;

    @CsExpose
    protected AccountNumber sender;

    @CsExpose
    protected String receiverName;

    @CsExpose
    protected AccountNumber receiver;

    @CsExpose
    protected Amount amount;

    @CsExpose
    protected Date transferDate;

    @CsExpose
    protected Info additionalInfo;

    @CsExpose
    protected String senderReference;

    @CsExpose
    protected Symbols symbols;

    @CsExpose
    protected List<String> flags;

    /**
     * Get sender name.
     *
     * @return the sender name
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Set sender name.
     *
     * @param senderName the sender name
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Get sender.
     *
     * @return the sender
     */
    public AccountNumber getSender() {
        return sender;
    }

    /**
     * Set sender.
     *
     * @param sender the sender
     */
    public void setSender(AccountNumber sender) {
        this.sender = sender;
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
     * Set receiver name.
     *
     * @param receiverName the receiver name
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * Get receiver.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Set receiver.
     *
     * @param receiver the receiver
     */
    public void setReceiver(AccountNumber receiver) {
        this.receiver = receiver;
    }

    /**
     * Get amount.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Set amount.
     *
     * @param amount the amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * Get transfer date.
     *
     * @return the transfer date
     */
    public Date getTransferDate() {
        return transferDate;
    }

    /**
     * Set transfer date.
     *
     * @param transferDate the transfer date
     */
    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    /**
     * Get additional info.
     *
     * @return the additional info
     */
    public Info getAdditionalInfo() {
        return additionalInfo;
    }

    /**
     * Set additional info.
     *
     * @param additionalInfo the additional info
     */
    public void setAdditionalInfo(Info additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    /**
     * Get sender reference.
     *
     * @return the sender reference
     */
    public String getSenderReference() {
        return senderReference;
    }

    /**
     * Set sender reference.
     *
     * @param senderReference the sender reference
     */
    public void setSenderReference(String senderReference) {
        this.senderReference = senderReference;
    }

    /**
     * Get symbols.
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * Set symbols.
     *
     * @param symbols the symbols
     */
    public void setSymbols(Symbols symbols) {
        this.symbols = symbols;
    }

    /**
     * Get flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Set flags.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }

    /**
     * Instantiates a new Domestic payment create request.
     *
     * @param builder the builder
     */
    private DomesticPaymentCreateRequest(Builder builder) {
        senderName = builder.senderName;
        sender = builder.sender;
        receiverName = builder.receiverName;
        receiver = builder.receiver;
        amount = builder.amount;
        transferDate = builder.transferDate;
        additionalInfo = builder.additionalInfo;
        senderReference = builder.senderReference;
        symbols = builder.symbols;
        flags = builder.flags;
    }

    /**
     * Instantiates a new Domestic payment create request.
     *
     * @param senderName      the sender name
     * @param sender          the sender
     * @param receiverName    the receiver name
     * @param receiver        the receiver
     * @param amount          the amount
     * @param transferDate    the transfer date
     * @param additionalInfo  the additional info
     * @param senderReference the sender reference
     * @param symbols         the symbols
     * @param flags           the flags
     */
    public DomesticPaymentCreateRequest(String senderName, AccountNumber sender, String receiverName, AccountNumber receiver, Amount amount, Date transferDate, Info additionalInfo, String senderReference, Symbols symbols, List<String> flags) {
        this.senderName = senderName;
        this.sender = sender;
        this.receiverName = receiverName;
        this.receiver = receiver;
        this.amount = amount;
        this.transferDate = transferDate;
        this.additionalInfo = additionalInfo;
        this.senderReference = senderReference;
        this.symbols = symbols;
        this.flags = flags;
    }

    /**
     * The type Builder.
     */
    public static final class Builder {
        private String senderName;
        private AccountNumber sender;
        private String receiverName;
        private AccountNumber receiver;
        private Amount amount;
        private Date transferDate;
        private Info additionalInfo;
        private String senderReference;
        private Symbols symbols;
        private List<String> flags;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {}

        /**
         * Set name of the sender.
         *
         * @param val the val
         * @return the sender name
         */
        public Builder setSenderName(String val) {
            senderName = val;
            return this;
        }

        /**
         * Set account number of the sender.
         *
         * @param val the val
         * @return the sender
         */
        public Builder setSender(AccountNumber val) {
            sender = val;
            return this;
        }

        /**
         * Set name of the payee.
         *
         * @param val the val
         * @return the receiver name
         */
        public Builder setReceiverName(String val) {
            receiverName = val;
            return this;
        }

        /**
         * Set account number of payee.
         *
         * @param val the val
         * @return the receiver
         */
        public Builder setReceiver(AccountNumber val) {
            receiver = val;
            return this;
        }

        /**
         * Set payment order amount.
         *
         * @param val the val
         * @return the amount
         */
        public Builder setAmount(Amount val) {
            amount = val;
            return this;
        }

        /**
         * Set optional date (in the future) when this payment has to be done.
         *
         * @param val the val
         * @return the transfer date
         */
        public Builder setTransferDate(Date val) {
            transferDate = val;
            return this;
        }

        /**
         * Set message for payee set during payment order creation. It is used to identify transaction on
         * receiver side. Array of texts 4x35
         *
         * @param val the val
         * @return the additional info
         */
        public Builder setAdditionalInfo(Info val) {
            additionalInfo = val;
            return this;
        }

        /**
         * Set message for me set during payment order creation.
         *
         * @param val the val
         * @return the sender reference
         */
        public Builder setSenderReference(String val) {
            senderReference = val;
            return this;
        }

        /**
         * Set information about the symbols.
         *
         * @param val the val
         * @return the symbols
         */
        public Builder setSymbols(Symbols val) {
            symbols = val;
            return this;
        }

        /**
         * Set array of optional Flag values depends on Payment order category, type.
         * Possible flags:
         * Flag	    Description
         * <p>
         * urgent	Flag indicating urgent payment order (in SEPA, SWIFT and maybe also in local bank
         * clearing systems) requested by client.
         * redoable	Flag indicating if payment order can be re-done.
         *
         * @param val the val
         * @return the flags
         */
        public Builder setFlags(List<String> val) {
            flags = val;
            return this;
        }

        /**
         * Build domestic payment create request.
         *
         * @return the domestic payment create request
         */
        public DomesticPaymentCreateRequest build() {return new DomesticPaymentCreateRequest(this);}
    }
}

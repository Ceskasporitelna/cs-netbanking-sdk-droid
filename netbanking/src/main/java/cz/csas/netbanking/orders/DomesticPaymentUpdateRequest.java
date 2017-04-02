package cz.csas.netbanking.orders;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;

/**
 * The type Domestic payment update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 17.05.16.
 */
public class DomesticPaymentUpdateRequest extends DomesticPaymentCreateRequest {

    @CsExpose
    private String id;

    private DomesticPaymentUpdateRequest(Builder builder) {
        super(builder.senderName, builder.sender, builder.receiverName, builder.receiver, builder.amount,
                builder.transferDate, builder.additionalInfo, builder.senderReference, builder.symbols, builder.flags);
    }

    /**
     * Instantiates a new Domestic payment update request.
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
    public DomesticPaymentUpdateRequest(String senderName, AccountNumber sender, String receiverName, AccountNumber receiver, Amount amount, Date transferDate, Info additionalInfo, String senderReference, Symbols symbols, List<String> flags) {
        super(senderName, sender, receiverName, receiver, amount, transferDate, additionalInfo, senderReference, symbols, flags);
    }

    void setId(String id) {
        this.id = id;
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
        public Builder() {
        }

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
        public DomesticPaymentUpdateRequest build() {
            return new DomesticPaymentUpdateRequest(this);
        }
    }
}

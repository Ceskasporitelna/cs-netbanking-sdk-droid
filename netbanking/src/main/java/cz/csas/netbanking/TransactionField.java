package cz.csas.netbanking;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Transaction field.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 11.04.16.
 */
public enum TransactionField implements HasValue {

    /**
     * Booking date transaction field.
     */
    BOOKING_DATE("bookingDate"),

    /**
     * Partner transaction field.
     */
    PARTNER("partner"),

    /**
     * Amount transaction field.
     */
    AMOUNT("amount"),

    /**
     * Currency transaction field.
     */
    CURRENCY("currency"),

    /**
     * Variable symbol transaction field.
     */
    VARIABLE_SYMBOL("variableSymbol"),

    /**
     * Constant symbol transaction field.
     */
    CONSTANT_SYMBOL("constantSymbol"),

    /**
     * Specific symbol transaction field.
     */
    SPECIFIC_SYMBOL("specificSymbol"),

    /**
     * Transaction type transaction field.
     */
    TRANSACTION_TYPE("transactionType"),

    /**
     * Note transaction field.
     */
    NOTE("note"),

    /**
     * Payment reference transaction field.
     */
    PAYMENT_REFERENCE("paymentReference"),

    /**
     * Sender reference transaction field.
     */
    SENDER_REFERENCE("senderReference"),

    /**
     * Card number transaction field.
     */
    CARD_NUMBER("cardNumber"),

    /**
     * Investment instrument name transaction field.
     */
    INVESTMENT_INSTRUMENT_NAME("investmentInstrumentName"),

    /**
     * Marked transaction field.
     */
    MARKED("marked"),

    /**
     * Valuation date transaction field.
     */
    VALUATION_DATE("valuationDate"),

    /**
     * Reference id transaction field.
     */
    REFERENCE_ID("referenceId"),

    /**
     * Location transaction field.
     */
    LOCATION("location"),

    /**
     * Other field.
     */
    OTHER(null);

    private String value;

    TransactionField(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

}

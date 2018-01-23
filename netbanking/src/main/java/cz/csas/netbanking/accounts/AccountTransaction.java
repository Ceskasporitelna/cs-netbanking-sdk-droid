package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Account transaction.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 22 /01/2018.
 */
public class AccountTransaction extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private AccountParty accountParty;

    @CsExpose
    private String additionalTexts;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Amount amountSender;

    @CsExpose
    private String bookingDate;

    @CsExpose
    private String bookingType;

    @CsExpose
    private Integer cardNumber;

    @CsExpose
    private String constantSymbol;

    @CsExpose
    private String currRateEURDate;

    @CsExpose
    private String currRateEURValue;

    @CsExpose
    private String description;

    @CsExpose
    private Boolean descriptionEditable;

    @CsExpose
    private String paymentReference;

    @CsExpose
    private String payeeNote;

    @CsExpose
    private String payerNote;

    @CsExpose
    private String specificSymbol;

    @CsExpose
    private String state;

    @CsExpose
    private String timestampId;

    @CsExpose
    private String transactionType;

    @CsExpose
    private String valuationDate;

    @CsExpose
    private String variableSymbol;

    /**
     * Transaction reference ID
     * Required
     * f.e. I141126DXHZ3T
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Account party object
     * Required
     *
     * @return account party
     */
    public AccountParty getAccountParty() {
        return accountParty;
    }

    /**
     * Additional info to transaction - not used
     *
     * @return additional texts
     */
    public String getAdditionalTexts() {
        return additionalTexts;
    }

    /**
     * Amount object
     * Required
     *
     * @return amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Amount sender object
     * Required
     *
     * @return amount sender
     */
    public Amount getAmountSender() {
        return amountSender;
    }

    /**
     * Date of booking
     * Required
     * f.e. 2014-11-26T00:00:00+01:00
     *
     * @return booking date
     */
    public Date getBookingDate() {
        return TimeUtils.getISO8601Date(bookingDate);
    }

    /**
     * Type of booking
     *
     * @return booking type
     */
    public String getBookingType() {
        return bookingType;
    }

    /**
     * Number of card used in transaction
     * Required
     * f.e. 0
     *
     * @return card number
     */
    public Integer getCardNumber() {
        return cardNumber;
    }

    /**
     * Constant symbol
     * Required
     * f.e. 0558
     *
     * @return constant symbol
     */
    public String getConstantSymbol() {
        return constantSymbol;
    }

    /**
     * Date of conversion
     *
     * @return curr rate eur date
     */
    public Date getCurrRateEURDate() {
        return TimeUtils.getISO8601Date(currRateEURDate);
    }

    /**
     * Conversion rates in EUR
     *
     * @return curr rate eur value
     */
    public String getCurrRateEURValue() {
        return currRateEURValue;
    }

    /**
     * User description of the transaction
     * Required
     * f.e. domácí platba
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * If true, then description could be changed using PUT method/false
     * Required
     * f.e. false
     *
     * @return description editable
     */
    public Boolean getDescriptionEditable() {
        return descriptionEditable;
    }

    /**
     * not used
     *
     * @return payment reference
     */
    public String getPaymentReference() {
        return paymentReference;
    }

    /**
     * Note for payee ("zpráva pro příjemce"). Up to 140 chars. For foreign transactions, additional
     * info about currency rate etc.
     * Required
     * f.e. note for payee
     *
     * @return payee note
     */
    public String getPayeeNote() {
        return payeeNote;
    }

    /**
     * Note for payer ("zpráva pro mě"). Up to 140 chars.
     * Required
     * f.e. note for payer
     *
     * @return the payer note
     */
    public String getPayerNote() {
        return payerNote;
    }

    /**
     * Specific symbol
     * Required
     * f.e. 55
     *
     * @return specific symbol
     */
    public String getSpecificSymbol() {
        return specificSymbol;
    }

    /**
     * Transaction state
     *
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * not used
     *
     * @return timestamp id
     */
    public String getTimestampId() {
        return timestampId;
    }

    /**
     * Type of transaction
     * Required
     * f.e. 54
     *
     * @return transaction type
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Date of valuation
     * Required
     * f.e. 2014-11-26T00:00:00+01:00
     *
     * @return valuation date
     */
    public Date getValuationDate() {
        return TimeUtils.getISO8601Date(valuationDate);
    }

    /**
     * Variable symbol
     * Required
     * f.e. 0000000009
     *
     * @return variable symbol
     */
    public String getVariableSymbol() {
        return variableSymbol;
    }
}

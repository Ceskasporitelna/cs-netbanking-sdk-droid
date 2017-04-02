package cz.csas.netbanking.cards;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Card provides information about Card.
 * Also provides resources for card:<br>
 * {@link CardDeliveryResource}<br>
 * {@link CardTransactionsResource}<br>
 * {@link CardActionResource}<br>
 * {@link CardLimitsResource}<br>
 * {@link CardSecure3DResource}<br>
 * {@link CardTransferResource}<br>
 * {@link CardAccountsResource}<br>
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Card extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    @CsExpose
    private String owner;

    @CsExpose
    private String number;

    @CsExpose
    private String sequenceNumber;

    @CsExpose
    private String productI18N;

    @CsExpose
    private String productCode;

    @CsExpose
    private Date expiryDate;

    @CsExpose
    private Date validFromDate;

    private CardState state;

    @CsExpose
    @CsSerializedName("state")
    private String stateRaw;

    private CardType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    private CardProvider provider;

    @CsExpose
    @CsSerializedName("provider")
    private String providerRaw;

    private LockReason lockReason;

    @CsExpose
    @CsSerializedName("lockReason")
    private String lockReasonRaw;

    private CardCharacteristic characteristic;

    @CsExpose
    @CsSerializedName("characteristic")
    private String characteristicRaw;

    @CsExpose
    private Amount limit;

    @CsExpose
    private Amount balance;

    @CsExpose
    private Amount outstandingAmount;

    @CsExpose
    private Amount minimalMonthlyAmount;

    @CsExpose
    private Date installmentDueDate;

    @CsExpose
    private CardMainAccount mainAccount;

    @CsExpose
    @CsSerializedName(value = "cz-overallCardAccountLimits")
    private CardAccountLimits czOverallCardAccountLimits;

    private CardDeliveryMode cardDeliveryMode;

    @CsExpose
    @CsSerializedName("cardDeliveryMode")
    private String cardDeliveryModeRaw;

    @CsExpose
    private List<String> features;

    @CsExpose
    private List<String> flags;

    /**
     * Get unique product identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get users product name. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Get product owner
     *
     * @return the owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Get card number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Get card sequence number. The number distinguishing between separate cards
     * (different plastic cards) with the same Primary Account Number (PAN).
     *
     * @return the sequence number
     */
    public String getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Get localized product name.
     *
     * @return the product name
     */
    public String getProductI18N() {
        return productI18N;
    }

    /**
     * Get internal product code.
     *
     * @return the product code
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Get expiration date of particular plastic card.
     *
     * @return the expiry date
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Get date from which this particular plastic card is valid.
     *
     * @return the valid from date
     */
    public Date getValidFromDate() {
        return validFromDate;
    }

    /**
     * Get current state of card.
     * Possible values: ACTIVE, INACTIVE (issued card not activated yet), TEMPORARY_BLOCKED.
     *
     * @return the state
     */
    public CardState getState() {
        if (state == null && stateRaw != null)
            state = EnumUtils.translateToEnum(CardState.class, stateRaw);
        return state;
    }

    /**
     * Get state raw.
     *
     * @return the state raw
     */
    public String getStateRaw() {
        return stateRaw;
    }

    /**
     * Get type of card: credit, debit/bankcard.
     * Possible Values: BANK_CARD (used for debit card too), CREDIT
     *
     * @return the type
     */
    public CardType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(CardType.class, typeRaw);
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
     * Get credit card provider/issuer: Erste Bank or external bank.
     * Possible Values: ERSTE_BANK, EXTERNAL
     *
     * @return the provider
     */
    public CardProvider getProvider() {
        if (provider == null && providerRaw != null)
            provider = EnumUtils.translateToEnum(CardProvider.class, providerRaw);
        return provider;
    }

    /**
     * Get provider raw.
     *
     * @return the provider raw
     */
    public String getProviderRaw() {
        return providerRaw;
    }

    /**
     * Get reason for locking the card.
     * Possible Values: THEFT, LOSS, FRAUD, OTHER
     *
     * @return the lock reason
     */
    public LockReason getLockReason() {
        if (lockReason == null && lockReasonRaw != null)
            lockReason = EnumUtils.translateToEnum(LockReason.class, lockReasonRaw);
        return lockReason;
    }

    /**
     * Get lock reason raw.
     *
     * @return the lock reason raw
     */
    public String getLockReasonRaw() {
        return lockReasonRaw;
    }

    /**
     * Get card characteristic.
     * Possible values: MAIN, AUTHORIZED
     *
     * @return the characteristics
     */
    public CardCharacteristic getCharacteristic() {
        if (characteristic == null && characteristicRaw != null)
            characteristic = EnumUtils.translateToEnum(CardCharacteristic.class, characteristicRaw);
        return characteristic;
    }

    /**
     * Get characteristic raw.
     *
     * @return the characteristic raw
     */
    public String getCharacteristicRaw() {
        return characteristicRaw;
    }

    /**
     * Get loan limit for credit card (shadow) account.
     *
     * @return the limit
     */
    public Amount getLimit() {
        return limit;
    }

    /**
     * Get disposable balance of current account linked to debit/bank card or Available balance of
     * credit card (disposable balance of shadow account). Not available for all cards or states
     * (locked, closed, unknown).
     *
     * @return the balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Get total outstanding/owed amount for credit card (the last known value).
     *
     * @return the outstanding amount
     */
    public Amount getOutstandingAmount() {
        return outstandingAmount;
    }

    /**
     * Get minimal installment repayment amount for credit card (at previous cycle end date).
     *
     * @return the minimal monthly amount
     */
    public Amount getMinimalMonthlyAmount() {
        return minimalMonthlyAmount;
    }

    /**
     * Get installment repayment due date.
     *
     * @return the installment due date
     */
    public Date getInstallmentDueDate() {
        return installmentDueDate;
    }

    /**
     * Get information about the main account.
     *
     * @return the main account
     */
    public CardMainAccount getMainAccount() {
        return mainAccount;
    }

    /**
     * Get information about the main account's limits.
     *
     * @return the overall card account limits
     */
    public CardAccountLimits getCzOverallCardAccountLimits() {
        return czOverallCardAccountLimits;
    }

    /**
     * Get indicates how a client receives their card and pin.
     * Possible values: BRANCH, HOME, OTHER_BRANCH, ADDRESS_ABROAD.
     *
     * @return the card delivery mode
     */
    public CardDeliveryMode getCardDeliveryMode() {
        if (cardDeliveryMode == null && cardDeliveryModeRaw != null)
            cardDeliveryMode = EnumUtils.translateToEnum(CardDeliveryMode.class, cardDeliveryModeRaw);
        return cardDeliveryMode;
    }

    /**
     * Get card delivery mode raw.
     *
     * @return the card delivery mode raw
     */
    public String getCardDeliveryModeRaw() {
        return cardDeliveryModeRaw;
    }

    /**
     * Get array of optional features valid for given card.
     * Possible features:
     * Feature	            Description
     * <p>
     * contactless	        Indicates whether contactless technology is available for this card
     * secureOnlineShopping	Indicates whether secure online shopping is available for this card
     * reissuePin	        Indicates whether reissuing a PIN is possible.
     * limitChange	        Indicates that card limits could be changed for this card.
     * temporaryLimitChange	Indicates that card temporary limits could be changed for this card.
     * onlineLocking	    Indicates whether a card can be locked online.
     * cardDelivery	        Indicates whether card delivery mode could be changed for this card
     * replacementCard	    Indicates that a replacement card could be ordered for this (bank) card.
     * onlineUnlocking	    Indicates whether a card can be unlocked online.
     *
     * @return the features
     */
    public List<String> getFeatures() {
        return features;
    }

    /**
     * Get array of optional Flag values depends on Card type.
     * Possible flags:
     * Flag	                        Description
     * notAccountOwner	            User is only holder of the card not owner of mainAccount
     * contactlessEnabled	        Indicates whether contactless functionality is active for given
     * card
     * secureOnlineShoppingEnabled	Indicates that 3D secure online shopping functionality is active
     * for this card
     * automaticReplacementOn	    Indicates whether automatic card replacement is active for given
     * card
     * activationAllowed	        Indicates that card can be activated.
     * electronicStatementAllowed	User may see the electronic statements list and download
     * statement for credit card (shadow account).
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }


    /**
     * Convenience method for getting detail of the card right from the list.
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Card> callback) {
        if (resource instanceof CardResource)
            ((CardResource) resource).get(callback);
    }

    /**
     * Convenience method for updating card's settings.
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(ChangeCardSettingsRequest request, CallbackWebApi<ChangeCardSettingsResponse> callback) {
        if (resource instanceof CardResource)
            ((CardResource) resource).update(request, callback);
    }

    /**
     * Convenience getter for getting card's delivery resource.
     *
     * @return the delivery resource
     */
    public CardDeliveryResource getDeliveryResource() {
        if (resource instanceof CardResource)
            return ((CardResource) resource).getDeliveryResource();
        return null;
    }

    /**
     * Convenience getter for getting card's transactions resource.
     *
     * @return the transactions resource
     */
    public TransactionsResource getTransactionsResource() {
        if (resource instanceof CardResource)
            return ((CardResource) resource).getTransactionsResource();
        return null;
    }

    /**
     * Convenience getter for getting card's actions resource.
     *
     * @return the actions resource
     */
    public CardActionResource getActionsResource() {
        if (resource instanceof CardResource)
            return ((CardResource) resource).getActionsResource();
        return null;
    }

    /**
     * Convenience getter for getting card's limits resource.
     *
     * @return the limits resource
     */
    public CardLimitsResource getLimitsResource() {
        if (resource instanceof CardResource)
            ((CardResource) resource).getLimitsResource();
        return null;
    }

    /**
     * Convenience getter for getting card's 3D Secure resource.
     *
     * @return the secure resource
     */
    public CardSecure3DResource getSecureResource() {
        if (resource instanceof CardResource)
            ((CardResource) resource).getSecure3DResource();
        return null;
    }

    /**
     * Convenience getter for getting card's transfer resource.
     *
     * @return the transfer resource
     */
    public CardTransferResource getTransferResource() {
        if (resource instanceof CardResource)
            return ((CardResource) resource).getTransferResource();
        return null;
    }

    /**
     * Convenience getter for getting card's accounts resource.
     *
     * @return the accounts resource
     */
    public CardAccountsResource getAccountsResource() {
        if (resource instanceof CardResource)
            ((CardResource) resource).getAccountsResource();
        return null;
    }

}

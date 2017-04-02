package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.Address;

/**
 * The type Change card delivery settings request is used to chenage card delivery settings.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class ChangeCardDeliverySettingsRequest extends WebApiRequest {

    private CardDeliveryMode cardDeliveryMode;

    @CsExpose
    @CsSerializedName("cardDeliveryMode")
    private String cardDeliveryModeRaw;

    @CsExpose
    private List<Confirmation> confirmations;

    @CsExpose
    private String branchId;

    @CsExpose
    private String deliveryPhone;

    @CsExpose
    private Address address;

    /**
     * Instantiates a new Change card delivery settings request.
     *
     * @param cardDeliveryMode the card delivery mode
     * @param confirmations    the confirmations
     */
    public ChangeCardDeliverySettingsRequest(CardDeliveryMode cardDeliveryMode, List<Confirmation> confirmations) {
        this.cardDeliveryModeRaw = cardDeliveryMode.getValue();
        this.confirmations = confirmations;
    }

    public ChangeCardDeliverySettingsRequest(String cardDeliveryMode, List<Confirmation> confirmations) {
        this.cardDeliveryModeRaw = cardDeliveryMode;
        this.confirmations = confirmations;
    }

    /**
     * Set indicates how a client receives their card and pin.
     * Possible values: BRANCH, HOME, OTHER_BRANCH, ADDRESS_ABROAD.
     *
     * @param cardDeliveryMode the card delivery mode
     */
    public void setCardDeliveryMode(CardDeliveryMode cardDeliveryMode) {
        this.cardDeliveryModeRaw = cardDeliveryMode.getValue();
    }

    /**
     * Set indicates how a client receives their card and pin.
     * Possible values: BRANCH, HOME, OTHER_BRANCH, ADDRESS_ABROAD.
     *
     * @param cardDeliveryMode the card delivery mode
     */
    public void setCardDeliveryMode(String cardDeliveryMode) {
        this.cardDeliveryModeRaw = cardDeliveryMode;
    }

    /**
     * Set information about the confirmation.
     *
     * @param confirmations the confirmations
     */
    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
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
     * Get information about the confirmation in list.
     *
     * @return the confirmations
     */
    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    /**
     * ID of a branch where card should be sent.
     *
     * @return the branch id
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Phone number of the client.
     *
     * @return the delivery phone
     */
    public String getDeliveryPhone() {
        return deliveryPhone;
    }

    /**
     * Address where card should be sent.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }
}

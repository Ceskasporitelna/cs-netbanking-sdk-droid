package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Address;

/**
 * The type Card delivery provides information about Card Delivery.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class CardDelivery extends WebApiEntity {

    private CardDeliveryMode cardDeliveryMode;

    @CsExpose
    @CsSerializedName("cardDeliveryMode")
    private String cardDeliveryModeRaw;

    @CsExpose
    private String branchId;

    @CsExpose
    private Address address;

    @CsExpose
    private List<Confirmation> confirmations;

    /**
     * Get card delivery mode.
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
     * Get identification of the branch where card will be ready to takeover.
     *
     * @return the branch id
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * Get address where card should be sent.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Get confirmations.
     *
     * @return the confirmations
     */
    public List<Confirmation> getConfirmations() {
        return confirmations;
    }
}

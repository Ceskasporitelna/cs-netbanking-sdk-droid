package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Change card limits request. Is used to change limits for card.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class ChangeCardLimitsRequest extends WebApiRequest {

    /*
     * Card limits to change
     */
    @CsExpose
    private List<CardLimit> limits;

    /**
     * Information about the confirmation
     */
    @CsExpose
    private List<Confirmation> confirmations;

    /**
     * Instantiates a new Change card limits request.
     *
     * @param limits        the limits
     * @param confirmations the confirmations
     */
    public ChangeCardLimitsRequest(List<CardLimit> limits, List<Confirmation> confirmations) {
        this.limits = limits;
        this.confirmations = confirmations;
    }

    /**
     * Get list of card limits to change.
     *
     * @return the limits
     */
    public List<CardLimit> getLimits() {
        return limits;
    }

    /**
     * Set list of card limits to change.
     *
     * @param limits the limits
     */
    public void setLimits(List<CardLimit> limits) {
        this.limits = limits;
    }

    /**
     * Get list of information about the confirmation.
     *
     * @return the confirmations
     */
    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    /**
     * Set list of information about the confirmation.
     *
     * @param confirmations the confirmations
     */
    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }
}

package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Issue card action request. Is used to issue card action.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class CardActionRequest extends WebApiRequest {

    private CardAction action;

    @CsExpose
    @CsSerializedName("action")
    private String actionRaw;

    private LockReason lockReason;

    @CsExpose
    @CsSerializedName("lockReason")
    private String lockReasonRaw;

    @CsExpose
    private List<Confirmation> confirmations;

    /**
     * Instantiates a new Card action request.
     *
     * @param action the action
     */
    public CardActionRequest(CardAction action) {
        this.actionRaw = action.getValue();
    }

    /**
     * Instantiates a new Issue card action request.
     *
     * @param action        the action
     * @param lockReason    the lock reason
     * @param confirmations the confirmations
     */
    public CardActionRequest(CardAction action, LockReason lockReason,
                             List<Confirmation> confirmations) {
        this.actionRaw = action.getValue();
        this.lockReasonRaw = lockReason.getValue();
        this.confirmations = confirmations;
    }

    /**
     * Instantiates a new Card action request.
     *
     * @param action        the action
     * @param lockReason    the lock reason
     * @param confirmations the confirmations
     */
    public CardActionRequest(String action, LockReason lockReason,
                             List<Confirmation> confirmations) {
        this.actionRaw = action;
        this.lockReasonRaw = lockReason.getValue();
        this.confirmations = confirmations;
    }

    /**
     * Instantiates a new Card action request.
     *
     * @param action        the action
     * @param lockReason    the lock reason
     * @param confirmations the confirmations
     */
    public CardActionRequest(CardAction action, String lockReason,
                             List<Confirmation> confirmations) {
        this.actionRaw = action.getValue();
        this.lockReasonRaw = lockReason;
        this.confirmations = confirmations;
    }

    /**
     * Instantiates a new Card action request.
     *
     * @param action        the action
     * @param lockReason    the lock reason
     * @param confirmations the confirmations
     */
    public CardActionRequest(String action, String lockReason,
                             List<Confirmation> confirmations) {
        this.actionRaw = action;
        this.lockReasonRaw = lockReason;
        this.confirmations = confirmations;
    }

    /**
     * Get action which should be issued.
     * Possible values are "REISSUE_PIN", "LOCK_CARD", "UNLOCK_CARD", "REPLACE_CARD",
     * "ACTIVATE_CARD", "SET_AUTOMATIC_REPLACEMENT_ON", "SET_AUTOMATIC_REPLACEMENT_OFF".
     *
     * @return the action
     */
    public CardAction getAction() {
        if (action == null && actionRaw != null)
            action = EnumUtils.translateToEnum(CardAction.class, actionRaw);
        return action;
    }

    /**
     * Get action raw.
     *
     * @return the action raw
     */
    public String getActionRaw() {
        return actionRaw;
    }

    /**
     * Set action which should be issued.
     * Possible values are "REISSUE_PIN", "LOCK_CARD", "UNLOCK_CARD", "REPLACE_CARD",
     * "ACTIVATE_CARD", "SET_AUTOMATIC_REPLACEMENT_ON", "SET_AUTOMATIC_REPLACEMENT_OFF".
     *
     * @param action the action
     */
    public void setAction(CardAction action) {
        this.actionRaw = action.getValue();
    }

    /**
     * Set action which should be issued.
     * Possible values are "REISSUE_PIN", "LOCK_CARD", "UNLOCK_CARD", "REPLACE_CARD",
     * "ACTIVATE_CARD", "SET_AUTOMATIC_REPLACEMENT_ON", "SET_AUTOMATIC_REPLACEMENT_OFF".
     *
     * @param action the action
     */
    public void setAction(String action) {
        this.actionRaw = action;
    }

    /**
     * Get reason why card should be locked.
     * Possible values are "THEFT" and "LOSS". Relevant only for action "LOCK_CARD".
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
     * Set reason why card should be locked.
     * Possible values are "THEFT" and "LOSS". Relevant only for action "LOCK_CARD".
     *
     * @param lockReason the lock reason
     */
    public void setLockReason(LockReason lockReason) {
        this.lockReasonRaw = lockReason.getValue();
    }

    /**
     * Set reason why card should be locked.
     * Possible values are "THEFT" and "LOSS". Relevant only for action "LOCK_CARD".
     *
     * @param lockReason the lock reason
     */
    public void setLockReason(String lockReason) {
        this.lockReasonRaw = lockReason;
    }

    /**
     * Get information about confirmations in list.
     *
     * @return the confirmations
     */
    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    /**
     * Set confirmations.
     *
     * @param confirmations the confirmations
     */
    public void setConfirmations(List<Confirmation> confirmations) {
        this.confirmations = confirmations;
    }
}

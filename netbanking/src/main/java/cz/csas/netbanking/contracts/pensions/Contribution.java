package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Contribution.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Contribution extends WebApiEntity {

    @CsExpose
    private Boolean employer;

    @CsExpose
    private Amount participantAmount;

    @CsExpose
    private Amount otherPersonAmount;

    /**
     * Indication whether employer contribution is set up.
     *
     * @return true if the employer contribution is set up
     */
    public Boolean getEmployer() {
        return employer;
    }

    /**
     * Participant contribution value.
     *
     * @return the participant amount
     */
    public Amount getParticipantAmount() {
        return participantAmount;
    }

    /**
     * Other person contribution value.
     *
     * @return the other person amount
     */
    public Amount getOtherPersonAmount() {
        return otherPersonAmount;
    }
}

package cz.csas.netbanking.contracts.insurances;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Contract event.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractEvent extends WebApiEntity {

    @CsExpose
    private String number;

    @CsExpose
    private Date creationDate;

    private ContractEventState state;

    @CsExpose
    @CsSerializedName("state")
    private String stateRaw;

    @CsExpose
    private String substate;

    @CsExpose
    private Date substateDate;

    @CsExpose
    private String substateInfo;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Date processingDate;

    @CsExpose
    private List<Indemnity> indemnities;

    /**
     * Insurance event number
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Creation date of the insurance event.
     *
     * @return the creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * State of the event. Possible values: REPORTED, ATTACHING_DOCS, IN_SOLUTION, CLOSED
     *
     * @return the state
     */
    public ContractEventState getState() {
        if (state == null && stateRaw != null)
            state = EnumUtils.translateToEnum(ContractEventState.class, stateRaw);
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
     * Substate of the event.
     *
     * @return the substate
     */
    public String getSubstate() {
        return substate;
    }

    /**
     * Date that relates to the insurance substate.
     *
     * @return the substate date
     */
    public Date getSubstateDate() {
        return substateDate;
    }

    /**
     * Substate information with text and date.
     *
     * @return the substate info
     */
    public String getSubstateInfo() {
        return substateInfo;
    }

    /**
     * Total amount for the insurance event
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * The date when thi event has been reported
     *
     * @return the processing date
     */
    public Date getProcessingDate() {
        return processingDate;
    }

    /**
     * List of indemnities related to the insurance event.
     *
     * @return the indemnities
     */
    public List<Indemnity> getIndemnities() {
        return indemnities;
    }
}

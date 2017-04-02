package cz.csas.netbanking.cards;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.ListResponse;
import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Change card limits response provides information about card limit change result.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl()
public class ChangeCardLimitsResponse extends ListResponse<CardLimit> implements Signable {

    private SigningObject signingObject;

    /**
     * Information about the confirmation
     */
    @CsExpose
    private List<Confirmation> confirmations;

    /**
     * Card's limits
     */
    @CsExpose
    private List<CardLimit> limits;

    /**
     * Gets confirmations.
     *
     * @return the confirmations
     */
    public List<Confirmation> getConfirmations() {
        return confirmations;
    }

    /**
     * Card's limits
     * @return
     */
    public List<CardLimit> getLimits() {
        return limits;
    }

    /**
     * Card's limits
     * @return
     */
    @Override
    protected List<CardLimit> getItems() {
        return limits;
    }

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

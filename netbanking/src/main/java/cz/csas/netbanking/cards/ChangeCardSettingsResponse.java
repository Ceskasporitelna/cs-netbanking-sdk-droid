package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Change card settings response provides information about card settings change result.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl()
public class ChangeCardSettingsResponse extends Card implements Signable {

    private SigningObject signingObject;

    @CsExpose
    private String branchId;

    /**
     * Get branch identifier.
     *
     * @return the branch id
     */
    public String getBranchId() {
        return branchId;
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

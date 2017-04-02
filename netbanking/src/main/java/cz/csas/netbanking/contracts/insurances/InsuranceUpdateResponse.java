package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.netbanking.contracts.insurances.Insurance;

/**
 * The type Insurance update response
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceUpdateResponse extends Insurance implements Signable {

    private SigningObject signingObject;

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

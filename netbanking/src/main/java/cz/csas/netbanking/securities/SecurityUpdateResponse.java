package cz.csas.netbanking.securities;

import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Signed security.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class SecurityUpdateResponse extends Security implements Signable {

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

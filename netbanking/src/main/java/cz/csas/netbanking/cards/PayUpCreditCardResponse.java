package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Pay up credit card response.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
@CsSignUrl()
public class PayUpCreditCardResponse implements Signable {

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

package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;
import cz.csas.netbanking.contracts.buildings.BuildingsContract;

/**
 * The type Signed buildings contract.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContractUpdateResponse extends BuildingsContract implements Signable{

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

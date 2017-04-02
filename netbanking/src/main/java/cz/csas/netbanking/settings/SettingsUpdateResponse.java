package cz.csas.netbanking.settings;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Settings update response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class SettingsUpdateResponse extends WebApiEntity implements Signable {

    private SigningObject signingObject;

    @CsExpose
    private Settings settings;

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }

    public Settings getSettings() {
        return settings;
    }
}

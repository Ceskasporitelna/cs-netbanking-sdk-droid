package cz.csas.netbanking.authorizationLimits;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Authorization limits list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class AuthorizationLimitsListResponse extends ListResponse<AuthorizationLimit> {

    @CsExpose
    @CsSerializedName(value = "limits", alternate = "items")
    private List<AuthorizationLimit> limits;

    @Override
    protected List<AuthorizationLimit> getItems() {
        return limits;
    }

    public List<AuthorizationLimit> getLimits() {
        return limits;
    }
}

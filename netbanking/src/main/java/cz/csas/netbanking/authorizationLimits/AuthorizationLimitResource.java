package cz.csas.netbanking.authorizationLimits;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Authorization limit resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class AuthorizationLimitResource extends InstanceResource implements GetEnabled<AuthorizationLimit> {

    public AuthorizationLimitResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Return local specific payment order entry limits valid for combination of user, authorization
     * method and used channel/application. For example user could define different limits for TAC
     * authorization via George and mobile applications.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<AuthorizationLimit> callback) {
        ResourceUtils.callGet(this, null, null, AuthorizationLimit.class, callback);
    }
}

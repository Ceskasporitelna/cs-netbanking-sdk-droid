package cz.csas.netbanking.authorizationLimits;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.ParametrizedListEnabled;

/**
 * The type Authorization limits resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class AuthorizationLimitsResource extends Resource implements ParametrizedListEnabled<AuthorizationLimitsParameters, AuthorizationLimitsListResponse>, HasInstanceResource<AuthorizationLimitResource> {

    public AuthorizationLimitsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get the resource of authorization limit with a given id
     *
     * @param id the id of the authorization limit
     * @return
     */
    @Override
    public AuthorizationLimitResource withId(Object id) {
        return new AuthorizationLimitResource(id, getBasePath(), getClient());
    }

    /**
     * Return all user local specific payment order entry limits for for all user active
     * authorization methods and channels/applications used in country.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(AuthorizationLimitsParameters parameters, CallbackWebApi<AuthorizationLimitsListResponse> callback) {
        ResourceUtils.callParametrizedList(this, parameters, null, AuthorizationLimitsListResponse.class, callback);
    }
}

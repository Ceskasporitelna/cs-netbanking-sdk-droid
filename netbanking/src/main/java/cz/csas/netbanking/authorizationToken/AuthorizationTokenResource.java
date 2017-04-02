package cz.csas.netbanking.authorizationToken;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;

/**
 * The type Authorization token resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class AuthorizationTokenResource extends Resource implements DeleteEnabled<WebApiEmptyResponse> {

    public AuthorizationTokenResource(String basePath, WebApiClient client) {
        super(basePath.replace("my/", ""), client);
    }

    /**
     * Invalidate authorization token.
     *
     * @param callback the callback
     */
    @Override
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        ResourceUtils.callDelete(this, "invalidate", null, null, WebApiEmptyResponse.class, callback);
    }
}

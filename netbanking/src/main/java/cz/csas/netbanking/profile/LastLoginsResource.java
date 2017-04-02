package cz.csas.netbanking.profile;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Last logins resource. This resource provides Last login information and data.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class LastLoginsResource extends Resource implements ListEnabled<LastLoginListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public LastLoginsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of last logins. Only the last login for particular channel/application.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<LastLoginListResponse> callback) {
        ResourceUtils.callList(this, null, LastLoginListResponse.class, callback);
    }
}

package cz.csas.netbanking.profile;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Profile resource. This resource provides Profile data and information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class ProfileResource extends Resource implements GetEnabled<Profile> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public ProfileResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get current user profile to get basic informations about current user.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Profile> callback) {
        ResourceUtils.callGet(this, null, null, Profile.class, callback);
    }

    /**
     * Last logins resource.
     *
     * @return the last logins resource
     */
    public LastLoginsResource getLastLogins() {
        return new LastLoginsResource(appendPathWith("logininfo"), getClient());
    }
}

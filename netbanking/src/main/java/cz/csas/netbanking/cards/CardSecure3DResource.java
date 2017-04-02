package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Secure resource.
 * This resource provides 3D secure online shopping status for card.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardSecure3DResource extends Resource implements GetEnabled<SecureSettings> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardSecure3DResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get 3D secure status online shopping status for card.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<SecureSettings> callback) {
        ResourceUtils.callGet(this, null, null, SecureSettings.class, callback);
    }
}

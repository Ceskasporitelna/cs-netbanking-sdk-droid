package cz.csas.netbanking.plugins;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Plugin resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class PluginResource extends InstanceResource implements UpdateEnabled<PluginUpdateRequest, PluginUpdateResponse> {

    public PluginResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Activation and deactivation of the specific plugin. You can also change settlement account
     * for given plugin and current user.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(PluginUpdateRequest request, CallbackWebApi<PluginUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, PluginUpdateResponse.class, callback);
    }
}

package cz.csas.netbanking.plugins;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Plugins resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class PluginsResource extends Resource implements PaginatedListEnabled<PaginatedParameters, PluginsListResponse>, HasInstanceResource<PluginResource> {

    public PluginsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Returns resource of plugin with a given id
     *
     * @param id the id the plugin
     * @return
     */
    @Override
    public PluginResource withId(Object id) {
        return new PluginResource(id, getBasePath(), getClient());
    }

    /**
     * Returns list of available plugins for current user. Plugin is application functionality which
     * can be enabled/disabled by user.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<PluginsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, PluginsListResponse.class, callback);
    }
}

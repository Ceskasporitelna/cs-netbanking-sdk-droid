package cz.csas.netbanking.settings;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Settings resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class SettingsResource extends Resource implements GetEnabled<Settings>, UpdateEnabled<SettingsUpdateRequest, SettingsUpdateResponse> {

    public SettingsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Returns basic user settings.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Settings> callback) {
        ResourceUtils.callGet(this, null, null, Settings.class, callback);
    }

    /**
     * Change user settings. Currently only language can be changed by this endpoint.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(SettingsUpdateRequest request, CallbackWebApi<SettingsUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, SettingsUpdateResponse.class, callback);
    }
}

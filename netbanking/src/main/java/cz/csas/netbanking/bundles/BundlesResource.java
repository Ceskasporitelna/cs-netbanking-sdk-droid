package cz.csas.netbanking.bundles;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;

/**
 * The type Bundles resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BundlesResource extends Resource implements CreateEnabled<BundleCreateRequest, Bundle> {

    public BundlesResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Resource for creating the bundle. Bundle represents set of payment orders which can be signed
     * at once. Number of items in bundle is limited to 50 items.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(BundleCreateRequest request, CallbackWebApi<Bundle> callback) {
        ResourceUtils.callCreate(this, request, null, Bundle.class, callback);
    }
}

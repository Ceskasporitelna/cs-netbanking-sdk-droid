package cz.csas.netbanking.promotions;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;
import cz.csas.cscore.webapi.apiquery.ListOfPrimitivesEnabled;

/**
 * The type Promotions resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class PromotionsResource extends Resource implements ListOfPrimitivesEnabled<PromotionsListResponse> {

    public PromotionsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Returns promotion list for the current user
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<PromotionsListResponse> callback) {
        ResourceUtils.callListOfPrimitives(this, null, null, PromotionsListResponse.class, callback);
    }

    /**
     * Promotion actions resource
     *
     * @return the resource
     */
    public PromotionsActionsResource getActionsResource() {
        //TODO: this is unclear in the doc. I created the actions resource so it would be possible to use the normal API methods
        return new PromotionsActionsResource(getBasePath().replace("promotions", "actions"), getClient());
    }
}

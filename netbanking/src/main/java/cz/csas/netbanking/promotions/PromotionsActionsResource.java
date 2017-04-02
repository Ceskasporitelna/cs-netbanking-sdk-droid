package cz.csas.netbanking.promotions;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;

/**
 * The type Promotions actions resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class PromotionsActionsResource extends Resource implements CreateEnabled<PromotionActionCreateRequest, PromotionActionCreateResponse> {

    public PromotionsActionsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    @Override
    public void create(PromotionActionCreateRequest request, CallbackWebApi<PromotionActionCreateResponse> callback) {
        ResourceUtils.callCreate(this, request, null, PromotionActionCreateResponse.class, callback);
    }
}

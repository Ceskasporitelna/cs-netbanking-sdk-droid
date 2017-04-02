package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Delivery resource. Provides Delivery settings for given card.
 * Also allows to update card delivery settings.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardDeliveryResource extends Resource implements GetEnabled<CardDelivery>,
        UpdateEnabled<ChangeCardDeliverySettingsRequest, ChangeCardDeliverySettingsResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardDeliveryResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource for given card delivery.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<CardDelivery> callback) {
        ResourceUtils.callGet(this, null, null, CardDelivery.class, callback);
    }

    /**
     * Update Card delivery settings
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(ChangeCardDeliverySettingsRequest request, CallbackWebApi<ChangeCardDeliverySettingsResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, ChangeCardDeliverySettingsResponse.class, callback);
    }
}

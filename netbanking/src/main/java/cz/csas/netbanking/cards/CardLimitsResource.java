package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Card limits resource.
 * This resource provides information about different limits for given card.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardLimitsResource extends Resource implements ListEnabled<CardLimitsListResponse>,
        UpdateEnabled<ChangeCardLimitsRequest, ChangeCardLimitsResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardLimitsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of all Card Limits
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<CardLimitsListResponse> callback) {
        ResourceUtils.callList(this, null, CardLimitsListResponse.class, callback);
    }

    /**
     * Change card limits according to given params.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(ChangeCardLimitsRequest request, CallbackWebApi<ChangeCardLimitsResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, ChangeCardLimitsResponse.class, callback);
    }
}

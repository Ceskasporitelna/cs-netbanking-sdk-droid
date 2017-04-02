package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Issue card action resource. This resource allows to issue various transactions on a card.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardActionResource extends Resource implements UpdateEnabled<CardActionRequest, CardActionResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardActionResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Issues action on card according to given params.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(CardActionRequest request, CallbackWebApi<CardActionResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, CardActionResponse.class, callback);
    }
}

package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.TransferResponse;

/**
 * The type Transfers resource. This resource provides Card transfer data and information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class CardTransferResource extends Resource implements
        UpdateEnabled<CardTransferRequest, TransferResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardTransferResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Update card transaction
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(CardTransferRequest request, CallbackWebApi<TransferResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, TransferResponse.class, callback);
    }
}

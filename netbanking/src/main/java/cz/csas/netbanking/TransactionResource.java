package cz.csas.netbanking;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Transaction resource provides Transaction information and data.
 * Also allows to add or change a client's personal transaction note and mark the transaction
 * as favorite/important for one specific transaction on selected account
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class TransactionResource extends InstanceResource implements UpdateEnabled<TransactionUpdateRequest, TransactionUpdateResponse> {

    /**
     * Instantiates a new Instance resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public TransactionResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Update account note and mark transactions.
     * Existing note will be removed if empty.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, TransactionUpdateResponse.class, callback);
    }
}

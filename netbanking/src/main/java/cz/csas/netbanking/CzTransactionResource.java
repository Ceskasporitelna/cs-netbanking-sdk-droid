package cz.csas.netbanking;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;


/**
 * The type Contracts transaction resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class CzTransactionResource extends InstanceResource implements UpdateEnabled<TransactionUpdateRequest, TransactionUpdateResponse> {

    public CzTransactionResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Allows to add or change a client's personal note and mark/star the transaction as
     * favorite/important for one specific transaction on selected product.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(TransactionUpdateRequest request, CallbackWebApi<TransactionUpdateResponse> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, TransactionUpdateResponse.class, callback);
    }
}

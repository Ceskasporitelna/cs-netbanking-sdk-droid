package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.TransferResponse;

/**
 * The type Transfers resource. This resource is used for revolving loan disbursement.
 * Currently only REVOLVING_LOAN subtype is supported by this resource. Requires signing.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountTransferResource extends Resource implements UpdateEnabled<AccountTransferRequest, TransferResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountTransferResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Revolve loan disbursement according to given request.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(AccountTransferRequest request, CallbackWebApi<TransferResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, TransferResponse.class, callback);
    }
}

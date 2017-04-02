package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Direct debit resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class AccountDirectDebitResource extends InstanceResource implements GetEnabled<DirectDebit>, DeleteEnabled<DirectDebitResponse> {

    public AccountDirectDebitResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Resource for deleting direct debit (permission) on certain account. Once signed no more
     * transfers can be made by receiver party.
     *
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void delete(CallbackWebApi<DirectDebitResponse> callback) {
        ResourceUtils.callDelete(this, null, null, DirectDebitResponse.class, callback);
    }

    /**
     * Get the single direct debits detail.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<DirectDebit> callback) {
        ResourceUtils.callGet(this, null, null, DirectDebit.class, callback);
    }
}

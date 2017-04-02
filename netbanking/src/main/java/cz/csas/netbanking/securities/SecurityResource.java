package cz.csas.netbanking.securities;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.CzTransactionsResource;

/**
 * The type Security resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class SecurityResource extends InstanceResource implements GetEnabled<Security>, UpdateEnabled<SecurityUpdateRequest, SecurityUpdateResponse> {

    public SecurityResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get a single securities account with all its details. Securities account represents virtual
     * account which holds securities titles and its shares (funds, bonds, etc.).
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Security> callback) {
        ResourceUtils.callGet(this, null, null, Security.class, callback);
    }

    /**
     * Allows to change a limited set of securities account-settings of one specific contract.
     * Currently only the field alias can be changed. Change only to alias field must not be signed,
     * but response is ready also for signing process.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(SecurityUpdateRequest request, CallbackWebApi<SecurityUpdateResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, SecurityUpdateResponse.class, callback);
    }

    /**
     * Returns security transactions resource
     *
     * @return the transactions resource
     */
    public CzTransactionsResource getTransactionsResource() {
        return new CzTransactionsResource(appendPathWith("transactions"), getClient());
    }
}

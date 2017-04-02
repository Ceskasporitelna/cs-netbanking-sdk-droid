package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Repayments resource provides list of repayments for given loan.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountRepaymentsResource extends Resource implements ListEnabled<RepaymentsListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountRepaymentsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Returns the list of Repayments. Only two last repayments are returned.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<RepaymentsListResponse> callback) {
        ResourceUtils.callList(this, null, RepaymentsListResponse.class, callback);
    }
}

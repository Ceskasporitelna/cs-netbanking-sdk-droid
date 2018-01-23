package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Account transactions history resource provides account transactions history data.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 22 /01/2018.
 */
public class AccountTransactionsHistoryResource extends Resource implements PaginatedListEnabled<AccountTransactionsParameters, AccountTransactionsListResponse> {

    /**
     * Instantiates a new resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountTransactionsHistoryResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Get list of transactions of an account according to given parameters.
     *
     * @param parameters account transactions history parameters
     * @param callback   callback
     */
    @Override
    public void list(AccountTransactionsParameters parameters, CallbackWebApi<AccountTransactionsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, AccountTransactionsListResponse.class, callback);
    }
}

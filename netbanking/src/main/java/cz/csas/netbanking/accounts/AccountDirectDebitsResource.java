package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Direct debits resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class AccountDirectDebitsResource extends Resource implements PaginatedListEnabled<DirectDebitsParameters, DirectDebitsListResponse>,
        HasInstanceResource<AccountDirectDebitResource>, CreateEnabled<DirectDebitCreateRequest, DirectDebitResponse> {

    public AccountDirectDebitsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Resource for creating (or allowing) direct debit on certain account. Once signed it can be
     * used by receiver party.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(DirectDebitCreateRequest request, CallbackWebApi<DirectDebitResponse> callback) {
        ResourceUtils.callCreate(this, request, null, DirectDebitResponse.class, callback);
    }

    /**
     * Get the resource of direct debit with a given id
     *
     * @param id the id of the direct debit
     * @return
     */
    @Override
    public AccountDirectDebitResource withId(Object id) {
        return new AccountDirectDebitResource(id, getBasePath(), getClient());
    }

    /**
     * Resource Direct Debit List represents collection of all direct debit approvals entered by
     * user for the specified user
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(DirectDebitsParameters parameters, CallbackWebApi<DirectDebitsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, DirectDebitsListResponse.class, callback);
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Account standing orders resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class AccountStandingOrdersResource extends Resource implements PaginatedListEnabled<StandingOrdersParameters, StandingOrdersListResponse>,
        HasInstanceResource<AccountStandingOrderResource>, CreateEnabled<StandingOrderCreateRequest, StandingOrderResponse> {

    public AccountStandingOrdersResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Resource for creating standing/sweep order. Once order has been signed new payments are
     * generated and executed according its settings.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(StandingOrderCreateRequest request, CallbackWebApi<StandingOrderResponse> callback) {
        ResourceUtils.callCreate(this, request, null, StandingOrderResponse.class, callback);
    }

    /**
     * Get the resource of standing order with a given id
     *
     * @param id the id of standing order
     * @return
     */
    @Override
    public AccountStandingOrderResource withId(Object id) {
        return new AccountStandingOrderResource(id, getBasePath(), getClient());
    }

    /**
     * Returns list of actual standing/sweep orders for accounts of the current user.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(StandingOrdersParameters parameters, CallbackWebApi<StandingOrdersListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, StandingOrdersListResponse.class, callback);
    }
}

package cz.csas.netbanking.budgets;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Budgets resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BudgetsResource extends Resource implements ListEnabled<BudgetsListResponse>, UpdateEnabled<BudgetsUpdateRequest, BudgetsListResponse> {

    public BudgetsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of user's tracked categories and its limits.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<BudgetsListResponse> callback) {
        ResourceUtils.callList(this, null, null, BudgetsListResponse.class, callback);
    }

    /**
     * Set new value of tracked categories.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(BudgetsUpdateRequest request, CallbackWebApi<BudgetsListResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, BudgetsListResponse.class, callback);
    }
}

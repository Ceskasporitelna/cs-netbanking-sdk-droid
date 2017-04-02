package cz.csas.netbanking.goals;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Goals resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class GoalsResource extends Resource implements ListEnabled<GoalsListResponse>, UpdateEnabled<GoalsUpdateRequest, GoalsListResponse> {

    public GoalsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns list of user's saving goals except of completed ones. In price, only CZK currency is
     * supported. If user has never set any goal, the response is empty.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<GoalsListResponse> callback) {
        ResourceUtils.callList(this, null, null, GoalsListResponse.class, callback);
    }

    /**
     * Set new value of goals. In price, only CZK currency is supported. If completed flag is not
     * present, false value is supposed. All goals of given client are replaced - old ones
     * (except of completed) are deleted and these new specified are inserted.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(GoalsUpdateRequest request, CallbackWebApi<GoalsListResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, GoalsListResponse.class, callback);
    }
}

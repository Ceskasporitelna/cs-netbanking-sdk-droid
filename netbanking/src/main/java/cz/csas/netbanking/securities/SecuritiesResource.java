package cz.csas.netbanking.securities;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Securities resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class SecuritiesResource extends Resource implements PaginatedListEnabled<PaginatedParameters, SecuritiesListResponse>,
        HasInstanceResource<SecurityResource> {

    public SecuritiesResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Returns list of securities accounts for current user. Securities account represents virtual
     * account which holds securities titles and its shares (funds, bonds, etc.).
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<SecuritiesListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, SecuritiesListResponse.class, callback);
    }

    /**
     * Get resource of security with a given id
     *
     * @param id the id of the security
     * @return security resource
     */
    @Override
    public SecurityResource withId(Object id) {
        return new SecurityResource(id, getBasePath(), getClient());
    }

}

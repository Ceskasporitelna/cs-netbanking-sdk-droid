package cz.csas.netbanking.templates;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Templates resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class TemplatesResource extends Resource implements PaginatedListEnabled<PaginatedParameters, TemplatesListResponse>, HasInstanceResource<TemplateResource> {

    public TemplatesResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get resource for template with a given id
     *
     * @param id the id of the template
     * @return
     */
    @Override
    public TemplateResource withId(Object id) {
        return new TemplateResource(id, getBasePath(), getClient());
    }

    /**
     * List of payment templates for current user.
     *
     * @param parameters the query parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<TemplatesListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, TemplatesListResponse.class, callback);
    }
}

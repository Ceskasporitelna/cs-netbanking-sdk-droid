package cz.csas.netbanking.templates;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Template resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class TemplateResource extends InstanceResource implements GetEnabled<Template> {

    public TemplateResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get payment template detail
     *
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void get(CallbackWebApi<Template> callback) {
        ResourceUtils.callGet(this, null, null, Template.class, callback);
    }
}

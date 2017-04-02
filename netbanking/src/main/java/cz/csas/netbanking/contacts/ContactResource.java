package cz.csas.netbanking.contacts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;

/**
 * The type Contact resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class ContactResource extends InstanceResource implements GetEnabled<Contact> {

    public ContactResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Resource represents one specific contact information identified by its id. It can be address,
     * phone or email address.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Contact> callback) {
        ResourceUtils.callGet(this, null, null, Contact.class, callback);
    }
}

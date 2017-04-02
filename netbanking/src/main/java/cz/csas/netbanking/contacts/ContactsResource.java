package cz.csas.netbanking.contacts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Contacts resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class ContactsResource extends Resource implements ListEnabled<ContactsListResponse>, HasInstanceResource<ContactResource> {

    public ContactsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get the resource of contact with a given id
     *
     * @param id the contact id
     * @return
     */
    @Override
    public ContactResource withId(Object id) {
        return new ContactResource(id, getBasePath(), getClient());
    }

    /**
     * Resource represents list of contact information for current user. It can contain addresses, phones and email addresses.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<ContactsListResponse> callback) {
        ResourceUtils.callList(this, null, null, ContactsListResponse.class, callback);
    }
}

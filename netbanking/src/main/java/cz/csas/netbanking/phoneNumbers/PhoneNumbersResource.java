package cz.csas.netbanking.phoneNumbers;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.ListEnabled;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Phone numbers resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class PhoneNumbersResource extends Resource implements ListEnabled<PhoneNumbersListResponse>,
        CreateEnabled<PhoneNumberCreateRequest, PhoneNumber>,
        HasInstanceResource<PhoneNumberResource> {

    public PhoneNumbersResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Get single phone number with a given id
     *
     * @param id the id of the phone number
     * @return the phone number
     */
    @Override
    public PhoneNumberResource withId(Object id) {
        return new PhoneNumberResource(id, getBasePath(), getClient());
    }

    /**
     * Returns list of phone numbers
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<PhoneNumbersListResponse> callback) {
        ResourceUtils.callList(this, null, PhoneNumbersListResponse.class, callback);
    }

    /**
     * Creates new phone number
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(PhoneNumberCreateRequest request, CallbackWebApi<PhoneNumber> callback) {
        ResourceUtils.callCreate(this, request, null, PhoneNumber.class, callback);
    }
}

package cz.csas.netbanking.phoneNumbers;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Phone number resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class PhoneNumberResource extends InstanceResource implements UpdateEnabled<PhoneNumberUpdateRequest, PhoneNumber>, DeleteEnabled<WebApiEmptyResponse> {

    public PhoneNumberResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Deletes phone number
     *
     * @param callback the callback
     */
    @Override
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        ResourceUtils.callDelete(this, null, null, WebApiEmptyResponse.class, callback);
    }

    /**
     * Updates phone number
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(PhoneNumberUpdateRequest request, CallbackWebApi<PhoneNumber> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, PhoneNumber.class, callback);
    }
}

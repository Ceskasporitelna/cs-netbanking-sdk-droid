package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Payment domestic resource. This resource is used for updating domestic payment order.
 * Active operation. Requires signing.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 17.05.16.
 */
public class PaymentDomesticResource extends InstanceResource implements UpdateEnabled<DomesticPaymentUpdateRequest, DomesticPaymentUpdateResponse> {
    public PaymentDomesticResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Update Domestic Payment Order according to given params.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(DomesticPaymentUpdateRequest request, CallbackWebApi<DomesticPaymentUpdateResponse> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, DomesticPaymentUpdateResponse.class, callback);
    }
}


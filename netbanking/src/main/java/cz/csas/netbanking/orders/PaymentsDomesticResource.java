package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Payments domestic resource. This resource provides Payments domestic data and information.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentsDomesticResource extends Resource implements HasInstanceResource<PaymentDomesticResource>, CreateEnabled<DomesticPaymentCreateRequest, DomesticPaymentCreateResponse> {

    /**
     * Instantiates a new Payments domestic resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentsDomesticResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource for Payment Domestic Order  with given identifier.
     *
     * @param id the id of InstanceResource
     * @return the payment domestic resource
     */
    @Override
    public PaymentDomesticResource withId(Object id) {
        return new PaymentDomesticResource(id, getBasePath(), getClient());
    }

    /**
     * Create Domestic Payment Order according to given params.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(DomesticPaymentCreateRequest request, CallbackWebApi<DomesticPaymentCreateResponse> callback) {
        ResourceUtils.callCreate(this, request, null, DomesticPaymentCreateResponse.class, callback);
    }
}

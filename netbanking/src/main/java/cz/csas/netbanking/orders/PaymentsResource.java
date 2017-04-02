package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * Payments resource. This resource provides Payment Orders data and information.
 * Available resources:
 * {@link PaymentBookingDateResource}
 * {@link PaymentsDomesticResource}
 * {@link PaymentLimitsResource}
 * {@link PaymentsMobileResource}
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentsResource extends Resource implements HasInstanceResource<PaymentResource>, PaginatedListEnabled<PaymentsParameters, PaymentsListResponse> {

    /**
     * Instantiates a new Orders resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource for payment with given identifier.
     *
     * @param id the id of InstanceResource
     * @return the payment resource
     */
    @Override
    public PaymentResource withId(Object id) {
        return new PaymentResource(id, getBasePath(), getClient());
    }

    /**
     * Get list of payments orders for accounts of current user according to given parameters.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaymentsParameters parameters, CallbackWebApi<PaymentsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, PaymentsListResponse.class, callback);
    }

    /**
     * Get payment booking date resource.
     *
     * @return the payment booking date resource
     */
    public PaymentBookingDateResource getBookingDateResource() {
        return new PaymentBookingDateResource(appendPathWith("bookingdate"), getClient());
    }

    /**
     * Get domestic resource.
     *
     * @return the domestic resource
     */
    public PaymentsDomesticResource getDomesticResource() {
        return new PaymentsDomesticResource(appendPathWith("domestic"), getClient());
    }


    /**
     * Get limits resource.
     *
     * @return the limits resource
     */
    public PaymentLimitsResource getLimitsResource() {
        return new PaymentLimitsResource(appendPathWith("limits"), getClient());
    }

    /**
     * Get mobile resource.
     *
     * @return the mobile resource
     */
    public PaymentsMobileResource getMobileResource() {
        return new PaymentsMobileResource(appendPathWith("mobile"), getClient());
    }
}

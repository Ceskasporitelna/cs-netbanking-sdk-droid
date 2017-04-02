package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;

/**
 * The type Payment Booking date resource. This resource is used to get current available booking date.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentBookingDateResource extends Resource {

    /**
     * Instantiates a new Payment booking date resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentBookingDateResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Request current available booking date (today or the next available bank working day)
     * based on the provided account and optional payment order category parameters.
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(PaymentBookingDateRequest request, CallbackWebApi<PaymentBookingDateResponse> callback) {
        PaymentBookingDateParameters parameters = new PaymentBookingDateParameters(request.getAccountId());
        ResourceUtils.callUpdate(this, null, parameters, request, null, PaymentBookingDateResponse.class, callback);
    }
}

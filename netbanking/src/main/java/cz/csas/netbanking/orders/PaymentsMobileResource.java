package cz.csas.netbanking.orders;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.CreateEnabled;

/**
 * The type Payments mobile resource. This resource provides creation of mobile payment.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public class PaymentsMobileResource extends Resource implements CreateEnabled<MobilePaymentRequest,
        MobilePaymentResponse> {

    /**
     * Instantiates a new Payments mobile resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public PaymentsMobileResource(String basePath, WebApiClient client) {
        // This is a special occasion, for this single call a "cz" needs to be inserted in the path :-/
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Create new mobile payment. For rrecharging the credit available on prepaid cards provided by
     * Vodafone, T-Mobile or O2 and paying for all Vodafone services, e.g. invoice or mobile deposit.
     * Requires signing. 
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void create(MobilePaymentRequest request, CallbackWebApi<MobilePaymentResponse> callback) {
        ResourceUtils.callCreate(this, request, null, MobilePaymentResponse.class, callback);
    }
}

package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;

/**
 * The type Orders resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 17.05.16.
 */
public class OrdersResource extends Resource {
    /**
     * Instantiates a new Orders resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public OrdersResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Gets payments resource.
     *
     * @return the payments resource
     */
    public PaymentsResource getPaymentsResource() {
        return new PaymentsResource(appendPathWith("payments"), getClient());
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Reservations resource provides list of reservations done on account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountReservationsResource extends Resource implements PaginatedListEnabled<PaginatedParameters,
        ReservationsListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public AccountReservationsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of reservations according to given parameters.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(PaginatedParameters parameters, CallbackWebApi<ReservationsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, ReservationsListResponse.class, callback);
    }
}

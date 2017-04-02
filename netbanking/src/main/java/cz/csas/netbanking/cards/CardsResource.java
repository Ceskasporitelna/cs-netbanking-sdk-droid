package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Cards resource. This resource provide informations about Cards for current user.
 * Also provides access to Card instance resource.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardsResource extends Resource implements HasInstanceResource<CardResource>,
        PaginatedListEnabled<CardsParameters, CardsListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of all cards (either debet or credit) for current user.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(CardsParameters parameters, CallbackWebApi<CardsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, CardsListResponse.class, callback);
    }

    /**
     * Get instance resource for Card with given identifier
     *
     * @param id the id of InstanceResource
     * @return the card resource
     */
    @Override
    public CardResource withId(Object id) {
        return new CardResource(id, getBasePath(), getClient());
    }
}

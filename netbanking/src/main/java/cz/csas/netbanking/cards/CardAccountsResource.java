package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Card accounts resource. This resource provides Card accounts data and information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class CardAccountsResource extends Resource implements HasInstanceResource<CardAccountResource> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardAccountsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource for Card Account based on given identifier.
     *
     * @param id the id of InstanceResource
     * @return
     */
    @Override
    public CardAccountResource withId(Object id) {
        return new CardAccountResource(id, getBasePath(), getClient());
    }
}

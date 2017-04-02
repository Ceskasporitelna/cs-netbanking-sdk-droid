package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;
import cz.csas.netbanking.TransactionResource;
import cz.csas.netbanking.TransactionsExportResource;

/**
 * The type Card transactions resource. This resource provides Card Transactions data and information.
 * Also provides {@link TransactionsExportResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class CardTransactionsResource extends Resource implements
        HasInstanceResource<TransactionResource> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public CardTransactionsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get instance resource for Card Transaction with given identifier.
     *
     * @param id the id of InstanceResource
     * @return card transaction resource
     */
    @Override
    public TransactionResource withId(Object id) {
        return new TransactionResource(id, getBasePath(), getClient());
    }


    /**
     * Get card transactions export resource.
     *
     * @return the export resource
     */
    public TransactionsExportResource getExportResource() {
        return new TransactionsExportResource(getBasePath(), getClient());
    }
}

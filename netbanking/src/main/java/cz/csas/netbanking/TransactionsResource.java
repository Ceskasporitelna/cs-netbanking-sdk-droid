package cz.csas.netbanking;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Transactions resource provides account transaction data and information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class TransactionsResource extends Resource implements
        HasInstanceResource<TransactionResource> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public TransactionsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get Instance resource of AccountTransactionResource using AccountTransaction id.
     *
     * @param id the identifier of Account transaction
     * @return the account transaction resource
     */
    @Override
    public TransactionResource withId(Object id) {
        return new TransactionResource(id, getBasePath(), getClient());
    }

    /**
     * Get account transactions export resource.
     *
     * @return the export resource
     */
    public TransactionsExportResource getExportResource() {
        return new TransactionsExportResource(getBasePath(), getClient());
    }
}

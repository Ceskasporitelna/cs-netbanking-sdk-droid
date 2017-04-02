package cz.csas.netbanking;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Contracts transactions resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class CzTransactionsResource extends Resource implements HasInstanceResource<CzTransactionResource> {

    public CzTransactionsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get contract transaction resource with a given id
     *
     * @param id the transaction id
     * @return the transaction resource
     */
    @Override
    public CzTransactionResource withId(Object id) {
        return new CzTransactionResource(id, getBasePath(), getClient());
    }

    /**
     * Export transaction history into signed pdf.
     *
     * @return the export resource
     */
    public TransactionsExportResource getExportResource() {
        return new TransactionsExportResource(getBasePath(), getClient());
    }
}

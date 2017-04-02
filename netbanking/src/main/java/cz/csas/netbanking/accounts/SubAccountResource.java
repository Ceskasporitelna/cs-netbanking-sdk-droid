package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.netbanking.StatementsResource;

/**
 * The type Sub account resource is instance resrouce for SubAccount.
 * Also provides {@link StatementsResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class SubAccountResource extends InstanceResource {

    /**
     * Instantiates a new Instance resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public SubAccountResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get statements resource.
     *
     * @return the statements resource
     */
    public SubAccountStatementsResource getStatementsResource() {
        return new SubAccountStatementsResource(appendPathWith("statements"), getClient());
    }
}

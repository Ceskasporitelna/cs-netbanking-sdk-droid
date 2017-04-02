package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.netbanking.StatementsResource;

/**
 * The type Card account resource. This resource provides Card account data and information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class CardAccountResource extends InstanceResource{

    /**
     * Instantiates a new Instance resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public CardAccountResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get statements resource.
     *
     * @return the statements resource
     */
    public StatementsResource getStatements(){
        return new StatementsResource(appendPathWith("statements"),getClient());
    }
}

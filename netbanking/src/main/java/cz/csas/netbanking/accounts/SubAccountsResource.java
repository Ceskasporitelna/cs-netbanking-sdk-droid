package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Sub accounts resource provides access to SubAccount instance resource.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class SubAccountsResource extends Resource implements HasInstanceResource<SubAccountResource> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public SubAccountsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Get instance resource of SubAccount with given id.
     *
     * @param id the id of InstanceResource
     * @return sub account resource
     */
    @Override
    public SubAccountResource withId(Object id) {
        return new SubAccountResource(id, getBasePath(), getClient());
    }
}

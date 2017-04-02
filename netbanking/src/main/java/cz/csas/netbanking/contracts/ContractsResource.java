package cz.csas.netbanking.contracts;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.netbanking.contracts.buildings.BuildingsContractsResource;
import cz.csas.netbanking.contracts.insurances.InsurancesContractsResource;
import cz.csas.netbanking.contracts.loyalty.LoyaltyContractsResource;
import cz.csas.netbanking.contracts.pensions.PensionsContractsResource;

/**
 * The type Contracts resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractsResource extends Resource {

    public ContractsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get buildings contracts resource
     *
     * @return the buildings resource
     */
    public BuildingsContractsResource getBuildingsResource() {
        return new BuildingsContractsResource(appendPathWith("buildings"), getClient());
    }

    /**
     * Get pensions contracts resource
     *
     * @return the pensions resource
     */
    public PensionsContractsResource getPensionsResource() {
        return new PensionsContractsResource(appendPathWith("pensions"), getClient());
    }

    /**
     * Get insurances contracts resource
     *
     * @return the insurances resource
     */
    public InsurancesContractsResource getInsurancesResource() {
        return new InsurancesContractsResource(appendPathWith("insurances"), getClient());
    }

    /**
     * Get loyalty contracts resource
     *
     * @return the loyalty resource
     */
    public LoyaltyContractsResource getLoyaltyResource() {
        return new LoyaltyContractsResource(appendPathWith("loyalty"), getClient());
    }
}

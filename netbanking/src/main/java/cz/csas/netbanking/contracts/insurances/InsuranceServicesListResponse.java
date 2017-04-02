package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Insurance services list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceServicesListResponse extends ListResponse<InsuranceService> {

    @CsExpose
    @CsSerializedName(value = "services", alternate = "items")
    private List<InsuranceService> services;

    @Override
    protected List<InsuranceService> getItems() {
        return services;
    }

    /**
     * Get services.
     *
     * @return the services
     */
    public List<InsuranceService> getServices() {
        return services;
    }
}

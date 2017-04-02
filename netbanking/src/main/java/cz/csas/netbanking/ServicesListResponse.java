package cz.csas.netbanking;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Service list response provides list of services.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class ServicesListResponse extends PaginatedListResponse<Service, ServicesListResponse> {

    @CsExpose
    @CsSerializedName(value = "services", alternate = "items")
    private List<Service> services;

    @Override
    protected List<Service> getItems() {
        return services;
    }

    /**
     * Get list services.
     * Convenience method for {@link #getItems()}
     *
     * @return the services
     */
    public List<Service> getServices() {
        return services;
    }
}

package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Insurance list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceListResponse extends PaginatedListResponse<Insurance, InsuranceListResponse> {

    @CsExpose
    @CsSerializedName(value = "insurances", alternate = "items")
    private List<Insurance> insurances;

    @Override
    protected List<Insurance> getItems() {
        return insurances;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }
}

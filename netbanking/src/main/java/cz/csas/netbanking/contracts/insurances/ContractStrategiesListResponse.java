package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Contract strategies list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractStrategiesListResponse extends ListResponse<ContractStrategy> {

    @CsExpose
    @CsSerializedName(value = "strategies", alternate = "items")
    private List<ContractStrategy> strategies;

    @Override
    protected List<ContractStrategy> getItems() {
        return strategies;
    }

    public List<ContractStrategy> getStrategies() {
        return strategies;
    }
}

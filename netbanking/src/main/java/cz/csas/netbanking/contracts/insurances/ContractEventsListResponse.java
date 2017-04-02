package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Events list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractEventsListResponse extends ListResponse<ContractEvent> {

    @CsExpose
    @CsSerializedName(value = "events", alternate = "items")
    private List<ContractEvent> events;

    @Override
    protected List<ContractEvent> getItems() {
        return events;
    }

    public List<ContractEvent> getEvents() {
        return events;
    }
}

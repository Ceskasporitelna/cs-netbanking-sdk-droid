package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Insurees list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsureesListResponse extends ListResponse<Insuree> {

    @CsExpose
    @CsSerializedName(value = "insurees", alternate = "items")
    private List<Insuree> insurees;

    @Override
    protected List<Insuree> getItems() {
        return insurees;
    }

    /**
     * Get insurees.
     *
     * @return the insurees
     */
    public List<Insuree> getInsurees() {
        return insurees;
    }
}

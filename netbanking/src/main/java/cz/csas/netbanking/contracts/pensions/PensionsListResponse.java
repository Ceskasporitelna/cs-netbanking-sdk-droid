package cz.csas.netbanking.contracts.pensions;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Pensions list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class PensionsListResponse extends PaginatedListResponse<Pension, PensionsListResponse> {

    @CsExpose
    @CsSerializedName(value = "pensions", alternate = "items")
    private List<Pension> pensions;

    /**
     * Get list of Pensions
     *
     * @return the list of pensions
     */
    @Override
    protected List<Pension> getItems() {
        return pensions;
    }

    /**
     * Get list of pensions.
     * Convenience method for {@link #getItems()}
     *
     * @return the list of pensions
     */
    public List<Pension> getPensions() {
        return pensions;
    }
}

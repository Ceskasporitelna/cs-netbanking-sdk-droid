package cz.csas.netbanking.contracts.buildings;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Buildings list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsListResponse extends PaginatedListResponse<BuildingsContract, BuildingsListResponse> {

    @CsExpose
    @CsSerializedName(value = "buildings", alternate = "items")
    private List<BuildingsContract> buildingsContracts;

    /**
     * Get list of Buildings
     *
     * @return the list of buildings
     */
    @Override
    protected List<BuildingsContract> getItems() {
        return buildingsContracts;
    }

    /**
     * Get list of buildings.
     * Convenience method for {@link #getItems()}
     *
     * @return the list of buildings
     */
    public List<BuildingsContract> getBuildingsContracts() {
        return buildingsContracts;
    }
}

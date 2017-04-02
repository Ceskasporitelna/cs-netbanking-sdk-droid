package cz.csas.netbanking.goals;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Goals list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class GoalsListResponse extends ListResponse<Goal>{

    @CsExpose
    @CsSerializedName(value = "goals", alternate = "items")
    private List<Goal> goals;

    @Override
    protected List<Goal> getItems() {
        return goals;
    }

    public List<Goal> getGoals() {
        return goals;
    }
}

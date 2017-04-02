package cz.csas.netbanking.goals;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Goals update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class GoalsUpdateRequest extends WebApiRequest {

    @CsExpose
    private List<Goal> goals;

    /**
     * Instantiates a new Goals update request.
     *
     * @param goals the goals
     */
    public GoalsUpdateRequest(List<Goal> goals) {
        this.goals = goals;
    }

    /**
     * Get goals.
     *
     * @return the goals
     */
    public List<Goal> getGoals() {
        return goals;
    }

    /**
     * Set goals.
     *
     * @param goals the goals
     */
    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}

package cz.csas.netbanking.budgets;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Budgets update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BudgetsUpdateRequest extends WebApiRequest {

    @CsExpose
    private List<Budget> budgets;

    /**
     * Instantiates a new Budgets update request.
     *
     * @param budgets the budgets
     */
    public BudgetsUpdateRequest(List<Budget> budgets) {
        this.budgets = budgets;
    }

    /**
     * Get budgets.
     *
     * @return the budgets
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Set budgets.
     *
     * @param budgets the budgets
     */
    public void setBudgets(List<Budget> budgets) {
        this.budgets = budgets;
    }
}

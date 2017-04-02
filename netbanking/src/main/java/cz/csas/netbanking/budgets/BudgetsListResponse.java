package cz.csas.netbanking.budgets;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Budgets list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BudgetsListResponse extends ListResponse<Budget> {

    @CsExpose
    @CsSerializedName(value = "budgets", alternate = "items")
    private List<Budget> budgets;

    @CsExpose
    private Boolean manuallySet;

    @Override
    protected List<Budget> getItems() {
        return budgets;
    }

    /**
     * Get budgets.
     * Convenience method for {@link #getItems()}
     *
     * @return the budgets
     */
    public List<Budget> getBudgets() {
        return budgets;
    }

    /**
     * Get manually set.
     *
     * @return the manually set
     */
    public Boolean getManuallySet() {
        return manuallySet;
    }
}

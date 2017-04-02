package cz.csas.netbanking.budgets;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Budget.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Budget extends WebApiEntity {

    @CsExpose
    private Category category;

    @CsExpose
    private Amount budget;

    public Budget(Category category, Amount budget) {
        this.category = category;
        this.budget = budget;
    }

    /**
     * Budget category.
     *
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Budget category.
     *
     * @param category the category
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Financial limit of the watched category per a given period.
     *
     * @return the budget
     */
    public Amount getBudget() {
        return budget;
    }

    /**
     * Financial limit of the watched category per a given period.
     *
     * @param budget the budget
     */
    public void setBudget(Amount budget) {
        this.budget = budget;
    }
}

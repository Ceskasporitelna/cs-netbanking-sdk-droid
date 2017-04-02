package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Standing orders parameters.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class StandingOrdersParameters extends PaginatedParameters implements Sortable<StandingOrdersSortableFields> {

    private SortParameter<StandingOrdersSortableFields> sortBy;

    public StandingOrdersParameters(Pagination pagination, SortParameter<StandingOrdersSortableFields> sortBy) {
        super(pagination);
        this.sortBy = sortBy;
    }

    @Override
    public SortParameter<StandingOrdersSortableFields> getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(SortParameter<StandingOrdersSortableFields> sortBy) {
        this.sortBy = sortBy;
    }
}

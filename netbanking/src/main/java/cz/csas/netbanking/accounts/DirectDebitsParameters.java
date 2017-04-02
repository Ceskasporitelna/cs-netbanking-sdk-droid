package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Direct debits parameters.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class DirectDebitsParameters extends PaginatedParameters implements Sortable<DirectDebitsSortableFields> {

    private SortParameter<DirectDebitsSortableFields> sortBy;

    public DirectDebitsParameters(Pagination pagination, SortParameter<DirectDebitsSortableFields> sortBy) {
        super(pagination);
        this.sortBy = sortBy;
    }

    @Override
    public SortParameter<DirectDebitsSortableFields> getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(SortParameter<DirectDebitsSortableFields> sortBy) {
        this.sortBy = sortBy;
    }
}

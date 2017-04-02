package cz.csas.netbanking;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Statements parameters defines parameters to sort statements.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class StatementsParameters extends PaginatedParameters implements Sortable<StatementsSortableFields> {

    private SortParameter<StatementsSortableFields> sortBy;

    /**
     * Instantiates a new Statements parameters.
     *
     * @param pagination the pagination
     * @param sortBy     the sortBy
     */
    public StatementsParameters(Pagination pagination, SortParameter<StatementsSortableFields> sortBy) {
        super(pagination);
        this.sortBy = sortBy;
    }

    @Override
    public SortParameter<StatementsSortableFields> getSortBy() {
        return this.sortBy;
    }

    @Override
    public void setSortBy(SortParameter<StatementsSortableFields> sortBy) {
        this.sortBy = sortBy;
    }

}

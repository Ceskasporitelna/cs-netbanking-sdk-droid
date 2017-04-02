package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Cards parameters defines sort and pagination parameters for cards.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardsParameters extends PaginatedParameters implements Sortable<CardsSortableFields> {

    private SortParameter<CardsSortableFields> sortBy;

    /**
     * Instantiates a new Cards parameters.
     *
     * @param pagination the pagination
     * @param sortBy     the sort by
     */
    public CardsParameters(Pagination pagination, SortParameter<CardsSortableFields> sortBy) {
        super(pagination);
        this.sortBy = sortBy;
    }

    @Override
    public SortParameter<CardsSortableFields> getSortBy() {
        return sortBy;
    }

    @Override
    public void setSortBy(SortParameter<CardsSortableFields> sortBy) {
        this.sortBy = sortBy;
    }
}

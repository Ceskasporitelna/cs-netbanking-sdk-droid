package cz.csas.netbanking.messages;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Messages parameters.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessagesParameters extends PaginatedParameters implements Sortable<MessagesSortableFields> {

    private SortParameter<MessagesSortableFields> sortBy;

    @Override
    public SortParameter<MessagesSortableFields> getSortBy() {
        return sortBy;
    }

    public MessagesParameters(Pagination pagination, SortParameter<MessagesSortableFields> sortBy) {
        super(pagination);
        this.sortBy = sortBy;
    }

    @Override
    public void setSortBy(SortParameter<MessagesSortableFields> sortBy) {
        this.sortBy = sortBy;
    }
}

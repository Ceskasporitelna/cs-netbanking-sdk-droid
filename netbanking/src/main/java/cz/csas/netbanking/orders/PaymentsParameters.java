package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Payment parameters is used to define pagination and sort parameters for Payment request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 21.04.16.
 */
public class PaymentsParameters extends PaginatedParameters implements Sortable<PaymentsSortableFields> {

    /**
     * Sorting info
     */
    private SortParameter<PaymentsSortableFields> sortParameter;

    /**
     * Instantiates a new Payment parameters.
     *
     * @param pagination    the pagination
     * @param sortParameter the sort parameter
     */
    public PaymentsParameters(Pagination pagination, SortParameter<PaymentsSortableFields> sortParameter) {
        super(pagination);
        this.sortParameter = sortParameter;
    }

    @Override
    public SortParameter<PaymentsSortableFields> getSortBy() {
        return sortParameter;
    }

    @Override
    public void setSortBy(SortParameter<PaymentsSortableFields> sortBy) {
        this.sortParameter = sortBy;
    }
}

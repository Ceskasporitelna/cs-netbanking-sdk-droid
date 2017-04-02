package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * Standing orders sortable fields. The only possible value is NEXT_EXECUTION_DATE, or OTHER
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum StandingOrdersSortableFields implements HasValue {

    /**
     * Sort by next execution date
     */
    NEXT_EXECUTION_DATE("nextExecutionDate"),

    /**
     * Sort by other field
     */
    OTHER(null);

    private String value;

    StandingOrdersSortableFields(String value) {
        this.value = value;
    }

    /**
     * Other standing orders sortable field.
     *
     * @param value the value
     * @return the standing orders sortable field
     */
    public static StandingOrdersSortableFields other(String value) {
        StandingOrdersSortableFields fields = StandingOrdersSortableFields.OTHER;
        fields.value = value;
        return fields;
    }

    /**
     * Get value
     *
     * @return
     */
    @Override
    public String getValue() {
        return value;
    }
}

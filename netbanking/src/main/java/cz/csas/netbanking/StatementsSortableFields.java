package cz.csas.netbanking;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Statements sortable fields.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 06.05.16.
 */
public enum StatementsSortableFields implements HasValue {

    /**
     * Statement date
     */
    STATEMENT_DATE("statementDate"),

    /**
     * Other sort parameters.
     */
    OTHER(null);

    private String value;

    StatementsSortableFields(String value) {
        this.value = value;
    }

    /**
     * Other user sort parameters.
     *
     * @param value the value
     * @return the user sort parameters
     */
    public static StatementsSortableFields other(String value) {
        StatementsSortableFields parameters = StatementsSortableFields.OTHER;
        parameters.setValue(value);
        return parameters;
    }

    @Override
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }
}

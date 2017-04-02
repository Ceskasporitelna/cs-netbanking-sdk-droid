package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Direct debits sortable fields.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public enum DirectDebitsSortableFields implements HasValue {

    /**
     * Sort by approval name chosen by the user.
     */
    ALIAS("alias"),

    /**
     * Sort by unit of the period cycle.
     */
    PERIOD_CYCLE("periodCycle"),

    /**
     * Sort by other field.
     */
    OTHER(null);

    private String value;

    DirectDebitsSortableFields(String value) {
        this.value = value;
    }

    /**
     * Other sortable field. Allows to sort by other field.
     *
     * @param value the value
     * @return the accounts sortable fields
     */
    public static DirectDebitsSortableFields other(String value) {
        DirectDebitsSortableFields fields = DirectDebitsSortableFields.OTHER;
        fields.value = value;
        return fields;
    }

    /**
     * Get value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}
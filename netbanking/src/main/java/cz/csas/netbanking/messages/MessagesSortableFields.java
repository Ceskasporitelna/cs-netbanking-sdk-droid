package cz.csas.netbanking.messages;

import cz.csas.cscore.webapi.HasValue;

/**
 * The messages sortable fields.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public enum MessagesSortableFields implements HasValue {

    /**
     * Sort by from field
     */
    FROM("from"),

    /**
     * Sort by date
     */
    DATE("date"),

    /**
     * Sort by id
     */
    ID("id"),

    /**
     * Sort by other field
     */
    OTHER(null);

    private String value;

    MessagesSortableFields(String value) {
        this.value = value;
    }

    /**
     * Other messages sortable fields.
     *
     * @param value the value
     * @return the messages sortable fields
     */
    public static MessagesSortableFields other(String value) {
        MessagesSortableFields fields = MessagesSortableFields.OTHER;
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

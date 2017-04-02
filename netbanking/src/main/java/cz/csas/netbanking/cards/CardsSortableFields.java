package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Cards sortable fields.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.05.16.
 */
public enum CardsSortableFields implements HasValue {

    /**
     * Sort cards by id.
     */
    ID("id"),
    /**
     * Sort cards by product.
     */
    PRODUCT("product"),
    /**
     * Sort cards by custom field.
     */
    OTHER(null);

    private String value;

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

    CardsSortableFields(String value) {
        this.value = value;
    }

    /**
     * Other cards sortable fields.
     *
     * @param value the value
     * @return the cards sortable fields
     */
    public static CardsSortableFields other(String value) {
        CardsSortableFields fields = CardsSortableFields.OTHER;
        fields.setValue(value);
        return fields;
    }
}

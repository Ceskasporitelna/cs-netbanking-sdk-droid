package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Accounts sortable fields.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 06.05.16.
 */
public enum AccountsSortableFields implements HasValue {

    /**
     * Sort by iban.
     */
    IBAN("iban"),

    /**
     * Sort by balance.
     */
    BALANCE("balance"),

    /**
     * Other sort field.
     */
    OTHER(null);

    private String value;

    AccountsSortableFields(String value) {
        this.value = value;
    }

    /**
     * Get value.
     * Basic values are iban and balance. Can be other.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

    /**
     * Set value to sort by.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Other sortable field. Allows to sort by other field.
     *
     * @param value the value
     * @return the accounts sortable fields
     */
    public static AccountsSortableFields other(String value) {
        AccountsSortableFields instance = AccountsSortableFields.OTHER;
        instance.setValue(value);
        return instance;
    }
}

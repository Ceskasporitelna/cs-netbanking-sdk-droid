package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Direct debit type.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum DirectDebitType implements HasValue {

    /**
     * Direct debit direct debit type.
     */
    DIRECT_DEBIT("DIRECT_DEBIT"),

    /**
     * Sipo direct debit type.
     */
    SIPO("SIPO"),

    /**
     * Other direct debit type.
     */
    OTHER(null);

    private String value;

    DirectDebitType(String value) {
        this.value = value;
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

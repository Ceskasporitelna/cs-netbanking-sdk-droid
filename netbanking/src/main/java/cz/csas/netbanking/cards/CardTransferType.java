package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card transfer type.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardTransferType implements HasValue {

    /**
     * Debt repayment card transfer type.
     */
    DEBT_REPAYMENT("DEBT_REPAYMENT"),

    /**
     * Other card transfer type.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card transfer type.
     *
     * @param value the value
     */
    CardTransferType(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

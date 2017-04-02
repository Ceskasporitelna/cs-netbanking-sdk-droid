package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the transfer. Possible values are PAY_PREMIUM, EXTRA_DEPOSIT, RECOMMENDED_DEPOSIT.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public enum InsuranceTransferType implements HasValue {

    /**
     * Pay premium insurance transfer type.
     */
    PAY_PREMIUM("PAY_PREMIUM"),

    /**
     * Extra deposit insurance transfer type.
     */
    EXTRA_DEPOSIT("EXTRA_DEPOSIT"),

    /**
     * Recommended deposit insurance transfer type.
     */
    RECOMMENDED_DEPOSIT("RECOMMENDED_DEPOSIT"),

    /**
     * Other insurance transfer type.
     */
    OTHER(null);

    private String value;

    InsuranceTransferType(String value) {
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

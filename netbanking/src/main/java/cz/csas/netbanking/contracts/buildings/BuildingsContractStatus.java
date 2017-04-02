package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.webapi.HasValue;

/**
 * Status of the contract. Possible values are ACTIVE and CLOSED.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum BuildingsContractStatus implements HasValue {

    /**
     * Active buildings contract status.
     */
    ACTIVE("ACTIVE"),

    /**
     * Closed buildings contract status.
     */
    CLOSED("CLOSED"),

    /**
     * Other buildings contract status.
     */
    OTHER(null);

    private String value;

    BuildingsContractStatus(String value) {
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

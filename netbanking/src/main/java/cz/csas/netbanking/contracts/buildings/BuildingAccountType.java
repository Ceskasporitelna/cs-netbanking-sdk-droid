package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.webapi.HasValue;

/**
 * Type of the account. Possible values are BUILD_SAVING and BUILD_LOAN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum BuildingAccountType implements HasValue {

    /**
     * Build saving building account type.
     */
    BUILD_SAVING("BUILD_SAVING"),

    /**
     * Build loan building account type.
     */
    BUILD_LOAN("BUILD_LOAN"),

    /**
     * Other building account type.
     */
    OTHER(null);

    private String value;

    BuildingAccountType(String value) {
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

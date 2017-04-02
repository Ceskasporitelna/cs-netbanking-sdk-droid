package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.webapi.HasValue;

/**
 * Shows, whether a investment program is active for life insurance product. The field can be
 * either blank or filled with 2 values - INVESTMENT_MANAGEMENT or CONSEQ
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public enum InvestmentProgram implements HasValue {

    /**
     * Investment management investment program.
     */
    INVESTMENT_MANAGEMENT("INVESTMENT_MANAGEMENT"),

    /**
     * Conseq investment program.
     */
    CONSEQ("CONSEQ"),

    /**
     * Other investment program.
     */
    OTHER(null);

    private String value;

    InvestmentProgram(String value) {
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

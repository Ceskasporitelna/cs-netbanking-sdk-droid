package cz.csas.netbanking.securities;

import cz.csas.cscore.webapi.HasValue;

/**
 * Security Product Type. Possible values: BOND, SHARE, FUND, IPO, OPTION, OTHER, INDEX,
 * CERTIFICATE, INVESTMENT,KNOCKOUT, UNKNOWN.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public enum SecurityType implements HasValue {

    /**
     * Bond security type.
     */
    BOND("BOND"),

    /**
     * Share security type.
     */
    SHARE("SHARE"),

    /**
     * Fund security type.
     */
    FUND("FUND"),

    /**
     * Ipo security type.
     */
    IPO("IPO"),

    /**
     * Option security type.
     */
    OPTION("OPTION"),

    /**
     * Index security type.
     */
    INDEX("INDEX"),

    /**
     * Certificate security type.
     */
    CERTIFICATE("CERTIFICATE"),

    /**
     * Investment security type.
     */
    INVESTMENT("INVESTMENT"),

    /**
     * Knockout security type.
     */
    KNOCKOUT("KNOCKOUT"),

    /**
     * Unknown security type.
     */
    UNKNOWN("UNKNOWN"),

    /**
     * Other security type.
     */
    OTHER(null);

    private String value;

    SecurityType(String value) {
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

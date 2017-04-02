package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Strategy fund.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class StrategyFund {

    @CsExpose
    private String code;

    @CsExpose
    private String name;

    @CsExpose
    private Double share;

    @CsExpose
    private String changeType;

    /**
     * Id of the fund
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Name of the fund.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Share in the fund. This is percentage value. 20 means 20%.
     *
     * @return the share
     */
    public Double getShare() {
        return share;
    }

    /**
     * Gets change type.
     *
     * @return the change type
     */
    public String getChangeType() {
        return changeType;
    }
}

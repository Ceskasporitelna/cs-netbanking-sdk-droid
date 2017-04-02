package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Strategy.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Strategy extends WebApiEntity {

    @CsExpose
    private Double conservative;

    @CsExpose
    private Double balanced;

    @CsExpose
    private Double dynamic;

    @CsExpose
    private Double stateBonds;

    /**
     * Conservative strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @return the conservative share value
     */
    public Double getConservative() {
        return conservative;
    }

    /**
     * Conservative strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @param conservative the conservative share value
     */
    public void setConservative(Double conservative) {
        this.conservative = conservative;
    }

    /**
     * Balanced strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @return the balanced share value
     */
    public Double getBalanced() {
        return balanced;
    }

    /**
     * Balanced strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @param balanced the balanced share value
     */
    public void setBalanced(Double balanced) {
        this.balanced = balanced;
    }

    /**
     * Dynamic strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @return the dynamic share value
     */
    public Double getDynamic() {
        return dynamic;
    }

    /**
     * Dynamic strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @param dynamic the dynamic share value
     */
    public void setDynamic(Double dynamic) {
        this.dynamic = dynamic;
    }

    /**
     * State bonds strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @return the state bonds share value
     */
    public Double getStateBonds() {
        return stateBonds;
    }

    /**
     * State bonds strategy share. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     * @param stateBonds the state bonds share value
     */
    public void setStateBonds(Double stateBonds) {
        this.stateBonds = stateBonds;
    }
}

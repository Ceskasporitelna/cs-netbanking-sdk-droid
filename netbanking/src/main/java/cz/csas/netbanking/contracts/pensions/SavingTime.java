package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Saving time.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class SavingTime extends WebApiEntity {

    @CsExpose
    private Double supplementary;

    @CsExpose
    private Double oldAge;

    @CsExpose
    private Double earlyRetirement;

    /**
     * Supplementary pension saving time.
     * @return the supplementary saving time
     */
    public Double getSupplementary() {
        return supplementary;
    }

    /**
     * Old-age pension saving time.
     * @return the old age saving time
     */
    public Double getOldAge() {
        return oldAge;
    }

    /**
     * Early-retirement saving time.
     * @return the early retirement saving time
     */
    public Double getEarlyRetirement() {
        return earlyRetirement;
    }
}

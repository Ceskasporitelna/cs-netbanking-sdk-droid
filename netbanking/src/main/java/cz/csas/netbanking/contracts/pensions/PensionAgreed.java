package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Pension agreed.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class PensionAgreed extends WebApiEntity {

    @CsExpose
    private Boolean oldAge;

    @CsExpose
    private Boolean disability;

    @CsExpose
    private Boolean earlyRetirement;


    /**
     * Indication whether old-age pension has been agreed.
     *
     * @return the old age
     */
    public Boolean getOldAge() {
        return oldAge;
    }

    /**
     * Indication whether disability pension has been agreed.
     *
     * @return the disability
     */
    public Boolean getDisability() {
        return disability;
    }

    /**
     * Indication whether early-retirement pension has been agreed.
     *
     * @return the early retirement
     */
    public Boolean getEarlyRetirement() {
        return earlyRetirement;
    }
}

package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Break period.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class BreakPeriod {

    @CsExpose
    private Date validFromDate;

    @CsExpose
    private Date validToDate;

    public BreakPeriod(Date validFromDate, Date validToDate) {
        this.validFromDate = validFromDate;
        this.validToDate = validToDate;
    }

    /**
     * Start date of break period. Standing order will not be processed from this date.
     *
     * @return the valid from date
     */
    public Date getValidFromDate() {
        return validFromDate;
    }

    /**
     * Start date of break period. Standing order will not be processed from this date.
     *
     * @param validFromDate the valid from date
     */
    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
    }

    /**
     * End date of break period. Standing order will not be processed to this date.
     *
     * @return the valid to date
     */
    public Date getValidToDate() {
        return validToDate;
    }

    /**
     * End date of break period. Standing order will not be processed to this date.
     *
     * @param validToDate the valid to date
     */
    public void setValidToDate(Date validToDate) {
        this.validToDate = validToDate;
    }
}

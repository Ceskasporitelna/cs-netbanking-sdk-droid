package cz.csas.netbanking.contracts.insurances;

import java.util.Date;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Risk sports activation update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class RiskSportsUpdateRequest extends WebApiRequest {

    @CsExpose
    private String dateFrom;

    @CsExpose
    private String dateTo;

    @CsExpose
    private String phoneNumber;

    public RiskSportsUpdateRequest(Date dateFrom, Date dateTo, String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.dateFrom = TimeUtils.getPlainDateString(dateFrom);
        this.dateTo = TimeUtils.getPlainDateString(dateTo);
    }

    public Date getDateFrom() {
        return TimeUtils.getPlainDate(dateFrom);
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = TimeUtils.getPlainDateString(dateFrom);
    }

    public Date getDateTo() {
        return TimeUtils.getPlainDate(dateTo);
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = TimeUtils.getPlainDateString(dateTo);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

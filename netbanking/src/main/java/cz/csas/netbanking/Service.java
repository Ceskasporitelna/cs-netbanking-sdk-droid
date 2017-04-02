package cz.csas.netbanking;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Service provides information about Service.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Service extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String nameI18N;

    @CsExpose
    private String iconGroup;

    @CsExpose
    private Date dateFrom;

    @CsExpose
    private Date dateTo;

    /**
     * Get the identifier of service.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get localized name of the service.
     *
     * @return the name
     */
    public String getNameI18N() {
        return nameI18N;
    }

    /**
     * Get information about service group. There is an icon defined for every group.
     *
     * @return the icon group
     */
    public String getIconGroup() {
        return iconGroup;
    }

    /**
     * Get date the service is active from.
     *
     * @return the date from
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Get date the service will be active till.
     *
     * @return the date to
     */
    public Date getDateTo() {
        return dateTo;
    }
}

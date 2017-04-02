package cz.csas.netbanking.profile;

import java.util.Date;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Last login info provides last login information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class LastLoginInfo extends WebApiEntity {

    @CsExpose
    private String channel;

    @CsExpose
    @CsSerializedName(value = "lastlogin")
    private String lastLogin;

    /**
     * Get channel of the last login.
     *
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Get date of the last login.
     *
     * @return the last login
     */
    public Date getLastLogin() {
        return TimeUtils.getISO8601Date(lastLogin);
    }
}

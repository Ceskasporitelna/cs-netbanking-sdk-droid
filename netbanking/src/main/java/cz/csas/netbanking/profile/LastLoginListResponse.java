package cz.csas.netbanking.profile;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Last login list response provides list of last login information.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class LastLoginListResponse extends ListResponse<LastLoginInfo> {

    @CsExpose
    @CsSerializedName(value = "lastlogin")
    private List<LastLoginInfo> lastLogin;

    @Override
    protected List<LastLoginInfo> getItems() {
        return lastLogin;
    }

    /**
     * Get description of profile login info.
     * Convenience method for {@link #getItems()}
     *
     * @return the last login info list
     */
    public List<LastLoginInfo> getLastLogin() {
        return lastLogin;
    }

}

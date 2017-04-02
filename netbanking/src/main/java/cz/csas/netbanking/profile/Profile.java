package cz.csas.netbanking.profile;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Profile provides information about profile.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Profile extends WebApiEntity {

    @CsExpose
    @CsSerializedName(value = "firstname")
    private String firstName;

    @CsExpose
    @CsSerializedName(value = "lastname")
    private String lastName;

    @CsExpose
    private String salutation;

    @CsExpose
    private String customerId;

    @CsExpose
    private Integer instituteId;

    private MarketingInfoAcceptance marketingInfoAcceptance;

    @CsExpose
    @CsSerializedName("marketingInfoAcceptance")
    private String marketingInfoAcceptanceRaw;

    @CsExpose
    private Gender gender;

    @CsExpose
    @CsSerializedName(value = "lastlogin")
    private String lastLogin;

    /**
     * Get user's first name
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Get user's last name
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Get user's name used for salutation
     *
     * @return the salutation
     */
    public String getSalutation() {
        return salutation;
    }

    /**
     * Get customer's id
     *
     * @return the customer id
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Get number of institute
     *
     * @return the institute id
     */
    public Integer getInstituteId() {
        return instituteId;
    }

    /**
     * Get the customer approved §107 telecommunication act.
     * Possible values: ACCEPTED, NOT_ACCEPTED, UNKNOWN.
     *
     * @return the marketing info acceptance
     */
    public MarketingInfoAcceptance getMarketingInfoAcceptance() {
        if (marketingInfoAcceptance == null && marketingInfoAcceptanceRaw != null)
            marketingInfoAcceptance = EnumUtils.translateToEnum(MarketingInfoAcceptance.class, marketingInfoAcceptanceRaw);
        return marketingInfoAcceptance;
    }

    /**
     * Get marketing info acceptance raw.
     *
     * @return the marketing info acceptance raw
     */
    public String getMarketingInfoAcceptanceRaw() {
        return marketingInfoAcceptanceRaw;
    }

    /**
     * Get user's gender.
     * Possible values: MALE, FEMALE, UNKNOWN.
     *
     * @return the gender
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Get date and time of the last login of customer. Common last login for all client applications -
     * George, QC, etc.
     *
     * @return the last login
     */
    public Date getLastLogin() {
        return TimeUtils.getISO8601Date(lastLogin);
    }
}

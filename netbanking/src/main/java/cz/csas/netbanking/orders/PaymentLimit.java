package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Limit.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class PaymentLimit extends WebApiEntity {

    private AuthorizationType authorizationType;

    @CsExpose
    @CsSerializedName("authorizationType")
    private String authorizationTypeRaw;

    private ChannelId channelId;

    @CsExpose
    @CsSerializedName("channelId")
    private String channelIdRaw;

    private ApplicationId applicationId;

    @CsExpose
    @CsSerializedName("applicationId")
    private String applicationIdRaw;


    @CsExpose
    private Amount remainingAmount;

    /**
     * Get remaining Daily amount which can be transferred using particular authorization method and
     * channel (_embedded AMOUNT type).
     *
     * @return the remaining amount
     */
    public Amount getRemainingAmount() {
        return remainingAmount;
    }

    /**
     * Get ID of the application via which this payment order was entered/modified the last time.
     * Possible values: GEORGE, ATM_PAYMENT, ATM_OTHER, GSM, BRANCH_FE, POST_OFFICE,
     * INTERNET_BANKING, TELEPHONE_BANKER, COLLECTION_BOX, VIDEO_BANKER and UNKNOWN.
     *
     * @return the application id
     */
    public ApplicationId getApplicationId() {
        if (applicationId == null && applicationIdRaw != null)
            applicationId = EnumUtils.translateToEnum(ApplicationId.class, applicationIdRaw);
        return applicationId;
    }

    /**
     * Get application id raw.
     *
     * @return the application id raw
     */
    public String getApplicationIdRaw() {
        return applicationIdRaw;
    }

    /**
     * Get ID of the channel via which this payment order was entered/modified the last time.
     * Possible values: NET_BANKING, ATM, MOBILE_BANKING, ATM, BRANCH, POST_OFFICE, CALL_CENTRE,
     * VIDEO_BANKING and UNKNOWN.
     *
     * @return the channel id
     */
    public ChannelId getChannelId() {
        if (channelId == null && channelIdRaw != null)
            channelId = EnumUtils.translateToEnum(ChannelId.class, channelIdRaw);
        return channelId;
    }

    /**
     * Get channel id raw.
     *
     * @return the channel id raw
     */
    public String getChannelIdRaw() {
        return channelIdRaw;
    }

    /**
     * Get authorization method type for which is limit defined.
     * ENUM: tac, tan, sms, gridCard, eok, displayCard, mToken. Other local authorization type has
     * to be defined.
     *
     * @return the authorization type
     */
    public AuthorizationType getAuthorizationType() {
        if (authorizationType == null && authorizationTypeRaw != null)
            authorizationType = EnumUtils.translateToEnum(AuthorizationType.class, authorizationTypeRaw);
        return authorizationType;
    }

    /**
     * Get authorization type raw.
     *
     * @return the authorization type raw
     */
    public String getAuthorizationTypeRaw() {
        return authorizationTypeRaw;
    }
}

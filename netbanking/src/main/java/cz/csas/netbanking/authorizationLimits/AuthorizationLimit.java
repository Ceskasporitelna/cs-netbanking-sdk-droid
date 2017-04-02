package cz.csas.netbanking.authorizationLimits;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.signing.AuthorizationType;
import cz.csas.netbanking.Amount;

/**
 * The type Authorization limit.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class AuthorizationLimit extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private AuthorizationType authorizationType;

    private ChannelId channelId;

    @CsExpose
    @CsSerializedName("channelId")
    private String channelIdRaw;

    private ApplicationId applicationId;

    @CsExpose
    @CsSerializedName("applicationId")
    private String applicationIdRaw;

    @CsExpose
    private Amount dailyLimit;

    @CsExpose
    private Amount transactionLimit;

    @CsExpose
    private Amount maxBankLimit;

    /**
     * Internal ID for limit definition for authorization type, channel, application. If internal
     * ID doesn't exist, ID could be generated using authorizationType, channelId and applicationId
     * values.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Authorization method type for which is limit defined. ENUM: tac, tan, sms, gridCard, eok,
     * displayCard, mToken other local authorization type has to be defined.
     *
     * @return the authorization type
     */
    public AuthorizationType getAuthorizationType() {
        return authorizationType;
    }

    /**
     * ID of the channel for which is limit defined. ENUM: netBanking, mobileBanking, homeBanking,
     * thirdParty, and unknown - limit valid for all channels, not particulary defined.
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
     * ID of the application for which is limit defined. ENUM: George, InternetBanking and unknown
     * - limit valid for all applications, not particulary defined.
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
     * Daily limit for particular authorization method
     *
     * @return the daily limit
     */
    public Amount getDailyLimit() {
        return dailyLimit;
    }

    /**
     * Transaction limit for particular authorization method.
     *
     * @return the transaction limit
     */
    public Amount getTransactionLimit() {
        return transactionLimit;
    }

    /**
     * Maximal daily limit for authorization method defined by bank.
     *
     * @return the max bank limit
     */
    public Amount getMaxBankLimit() {
        return maxBankLimit;
    }

    /**
     * Convenience method for fetching authorization limit detail.
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<AuthorizationLimit> callback) {
        if (resource instanceof AuthorizationLimitResource)
            ((AuthorizationLimitResource) resource).get(callback);
    }
}

package cz.csas.netbanking.cards;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Card resource. This resource provides Card information and data.
 * Also allows to update the card.
 * Available resources:
 * {@link CardDeliveryResource}
 * {@link TransactionsResource}
 * {@link CardActionResource}
 * {@link CardLimitsResource}
 * {@link CardSecure3DResource}
 * {@link CardTransferResource}
 * {@link CardAccountsResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class CardResource extends InstanceResource implements GetEnabled<Card>, UpdateEnabled<ChangeCardSettingsRequest, ChangeCardSettingsResponse> {

    /**
     * Instantiates a new Instance resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public CardResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get card detail
     *
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void get(CallbackWebApi<Card> callback) {
        ResourceUtils.callGet(this, null, null, Card.class, callback);
    }

    /**
     * Update card settings according to given params.
     *
     * @param request  the request of type WebApiRequest
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void update(ChangeCardSettingsRequest request, CallbackWebApi<ChangeCardSettingsResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, ChangeCardSettingsResponse.class, callback);
    }

    /**
     * Get delivery resource.
     *
     * @return the delivery resource
     */
    public CardDeliveryResource getDeliveryResource() {
        return new CardDeliveryResource(appendPathWith("delivery"), getClient());
    }

    /**
     * Get card transactions resource.
     *
     * @return the card transactions resource
     */
    public TransactionsResource getTransactionsResource() {
        return new TransactionsResource(appendPathWith("transactions"), getClient());
    }

    /**
     * Get issue card action resource.
     *
     * @return the issue card action resource
     */
    public CardActionResource getActionsResource() {
        return new CardActionResource(appendPathWith("states"), getClient());
    }

    /**
     * Get card limits resource.
     *
     * @return the card limits resource
     */
    public CardLimitsResource getLimitsResource() {
        return new CardLimitsResource(appendPathWith("card-limits"), getClient());
    }

    /**
     * Get secure resource.
     *
     * @return the secure resource
     */
    public CardSecure3DResource getSecure3DResource() {
        return new CardSecure3DResource(appendPathWith("secure-online-shopping"), getClient());
    }

    /**
     * Get transfer card transfer resource.
     *
     * @return the card transfer resource
     */
    public CardTransferResource getTransferResource() {
        return new CardTransferResource(appendPathWith("transfer"), getClient());
    }

    /**
     * Get card accounts resource.
     *
     * @return the card accounts resource
     */
    public CardAccountsResource getAccountsResource() {
        return new CardAccountsResource(appendPathWith("mainaccount"), getClient());
    }
}

package cz.csas.netbanking.messages;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.client.rest.client.Response;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.Transform;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.apiquery.DeleteEnabled;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;

/**
 * The type Message resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessageResource extends InstanceResource implements GetEnabled<Message>, UpdateEnabled<MessageUpdateRequest, WebApiEmptyResponse>, DeleteEnabled<WebApiEmptyResponse> {

    public MessageResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Resource for deleting message by its identifier. Only read messages can be deleted.
     *
     * @param callback the callback
     */
    @Override
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        ResourceUtils.callDelete(this, null, null, WebApiEmptyResponse.class, callback);
    }

    /**
     * Get one specific messages for current user generated by bank itself. Message can be read or
     * unread, mandatory and non-mandatory.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Message> callback) {
        ResourceUtils.callGet(this, null, new Transform<Message>() {
            @Override
            protected Message doTransform(Message entity, CsSDKError error, Response response) throws CsSDKError {
                if (entity != null) {
                    for (Attachment attachment : entity.getAttachments()) {
                        attachment.setMessage(entity);
                    }
                }
                return entity;
            }
        }, Message.class, callback);
    }

    /**
     * After message has been read by user it should be marked accordingly by this endpoint.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(MessageUpdateRequest request, CallbackWebApi<WebApiEmptyResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, WebApiEmptyResponse.class, callback);
    }

    /**
     * Get messages attachments resource
     *
     * @return the attachments resource
     */
    public MessageAttachmentsResource getAttachmentsResource() {
        return new MessageAttachmentsResource(appendPathWith("attachments"), getClient());
    }
}

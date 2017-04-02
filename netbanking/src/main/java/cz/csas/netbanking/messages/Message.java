package cz.csas.netbanking.messages;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Message.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class Message extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String from;

    @CsExpose
    private String subject;

    @CsExpose
    private String date;

    @CsExpose
    private String body;

    @CsExpose
    private List<Attachment> attachments;

    @CsExpose
    private List<String> flags;

    /**
     * Message identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Name of the message sender. For example source system of the message.
     *
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * Message subject.
     *
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Date when message was sent/generated.
     *
     * @return the date
     */
    public Date getDate() {
        return TimeUtils.getISO8601Date(date);
    }

    /**
     * Body of the message. Body is html code. It is up to FE application to properly display it.
     *
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * Array of message attachments.
     *
     * @return the attachments
     */
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * Array of flags for messages.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Convenience get method for fetching message detail
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Message> callback) {
        if (resource instanceof MessageResource)
            ((MessageResource) resource).get(callback);
    }

    /**
     * Convenience delete method for deleting message
     *
     * @param callback the callback
     */
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        if (resource instanceof MessageResource)
            ((MessageResource) resource).delete(callback);
    }

    /**
     * Convenience update method for updating message
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(MessageUpdateRequest request, CallbackWebApi<WebApiEmptyResponse> callback) {
        if (resource instanceof MessageResource)
            ((MessageResource) resource).update(request, callback);
    }
}

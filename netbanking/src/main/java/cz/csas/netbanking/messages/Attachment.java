package cz.csas.netbanking.messages;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiStream;

/**
 * The type Attachment.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class Attachment {

    @CsExpose
    private String id;

    @CsExpose
    private String fileName;

    private Message message;

    /**
     * Attachment identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * File name of the attachment.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get message.
     *
     * @return the message
     */
    Message getMessage() {
        return message;
    }

    /**
     * Set message.
     *
     * @param message the message
     */
    void setMessage(Message message) {
        this.message = message;
    }

    /**
     * Downloads attachment file. The binary representation of an attachment file, with a
     * “Content-Disposition” header of type attachment (including the filename), in order to
     * instruct the browser to open a save dialog.
     *
     * @param callback the callback
     */
    public void download(CallbackWebApi<WebApiStream> callback) {
        if (message.getResource() instanceof MessageResource)
            ((MessageResource) message.getResource()).getAttachmentsResource().withId(getId()).download(callback);
        if (message.getResource() instanceof MessagesResource && getMessage() != null)
            ((MessagesResource) message.getResource()).withId(getMessage().getId()).getAttachmentsResource().withId(getId()).download(callback);
    }
}

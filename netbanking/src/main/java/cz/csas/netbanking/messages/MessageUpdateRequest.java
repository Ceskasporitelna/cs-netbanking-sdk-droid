package cz.csas.netbanking.messages;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Update message request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessageUpdateRequest extends WebApiRequest {

    @CsExpose
    private Boolean read;

    /**
     * Instantiates a new Message update request.
     *
     * @param read the read
     */
    public MessageUpdateRequest(Boolean read) {
        this.read = read;
    }

    /**
     * Get read.
     *
     * @return the read
     */
    public Boolean getRead() {
        return read;
    }

    /**
     * Set read.
     *
     * @param read the read
     */
    public void setRead(Boolean read) {
        this.read = read;
    }
}

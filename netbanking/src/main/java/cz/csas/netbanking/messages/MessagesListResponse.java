package cz.csas.netbanking.messages;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Messages list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessagesListResponse extends PaginatedListResponse<Message, MessagesListResponse> {

    @CsExpose
    @CsSerializedName(value = "messages", alternate = "items")
    private List<Message> messages;

    @Override
    protected List<Message> getItems() {
        return messages;
    }

    /**
     * Get messages.
     *
     * @return the messages
     */
    public List<Message> getMessages() {
        return messages;
    }
}

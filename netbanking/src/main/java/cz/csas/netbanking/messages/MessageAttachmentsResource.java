package cz.csas.netbanking.messages;

import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.HasInstanceResource;

/**
 * The type Message attachments resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessageAttachmentsResource extends Resource implements HasInstanceResource<MessageAttachmentResource> {

    public MessageAttachmentsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get the resource of attachments
     *
     * @param id the id of the attachment
     * @return the attachment resource
     */
    @Override
    public MessageAttachmentResource withId(Object id) {
        return new MessageAttachmentResource(id, getBasePath(), getClient());
    }
}

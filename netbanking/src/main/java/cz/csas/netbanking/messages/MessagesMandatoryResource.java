package cz.csas.netbanking.messages;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Messages mandatory resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessagesMandatoryResource extends Resource implements ListEnabled<MessagesListResponse> {

    public MessagesMandatoryResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Returns all mandatory messages. This call might return different messages based on appId of
     * the caller (for example, some messages might be specific to an application). Which messages
     * can be seen by which application can be configured on the presto server side.
     *
     * @param callback the callback
     */
    @Override
    public void list(CallbackWebApi<MessagesListResponse> callback) {
        ResourceUtils.callList(this, null, null, MessagesListResponse.class, callback);
    }
}

package cz.csas.netbanking.messages;

import java.util.HashMap;
import java.util.Map;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.Method;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiStream;

/**
 * The type Message attachment resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class MessageAttachmentResource extends InstanceResource {

    public MessageAttachmentResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Downloads attachment file. The binary representation of an attachment file, with a
     * “Content-Disposition” header of type attachment (including the filename), in order to
     * instruct the browser to open a save dialog.
     *
     * @param callback
     */
    public void download(CallbackWebApi<WebApiStream> callback) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-type", "application/pdf");
        ResourceUtils.callDownload(Method.POST, this, null, headers, null, callback);
    }
}

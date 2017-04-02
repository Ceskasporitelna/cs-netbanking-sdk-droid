package cz.csas.netbanking;

import java.util.HashMap;
import java.util.Map;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Method;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiStream;

/**
 * The type Transactions export resource. This resource is used to export transaction history
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class TransactionsExportResource extends Resource {

    /**
     * Instantiates a new Building transactions export resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public TransactionsExportResource(String basePath, WebApiClient client) {
        // This is a special occasion, for this single call a "cz" needs to be inserted in the path :-/
        super(basePath.replace("netbanking/my", "netbanking/cz/my"), client);
    }

    /**
     * Export transactions according to given parameters.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    public void export(ExportTransactionsParameters parameters, CallbackWebApi<WebApiStream> callback) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("content-type", "application/pdf");
        ResourceUtils.callDownload(Method.POST, this, "export", parameters, null, headers, null, callback);
    }
}

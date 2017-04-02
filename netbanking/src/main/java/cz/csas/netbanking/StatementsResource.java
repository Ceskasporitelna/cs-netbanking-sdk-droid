package cz.csas.netbanking;

import java.util.HashMap;
import java.util.Map;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Method;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.WebApiStream;
import cz.csas.cscore.webapi.apiquery.PaginatedListEnabled;

/**
 * The type Statements resource. This resource provides list of statements for account or card account.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class StatementsResource extends Resource implements PaginatedListEnabled<StatementsParameters, StatementsListResponse> {

    /**
     * Instantiates a new Resource.
     *
     * @param basePath the base path
     * @param client   the client
     */
    public StatementsResource(String basePath, WebApiClient client) {
        super(basePath, client);
    }

    /**
     * Get list of all statements according to given parameters. Can be empty.
     * Statement history is limited for the last 24 months.
     *
     * @param parameters the parameters
     * @param callback   the callback
     */
    @Override
    public void list(StatementsParameters parameters, CallbackWebApi<StatementsListResponse> callback) {
        ResourceUtils.callPaginatedList(this, parameters, null, StatementsListResponse.class, callback);
    }

    /**
     * Download statements according to given parameters.
     *
     * @param parameters the statement parameters
     * @param callback   the callback
     */
    public void download(StatementsDownloadParameters parameters, CallbackWebApi<WebApiStream> callback) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/pdf");
        ResourceUtils.callDownload(Method.POST, this, "signed/download", parameters, null, headers, null, callback);
    }
}
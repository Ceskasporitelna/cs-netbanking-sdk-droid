package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.CzTransactionsResource;

/**
 * The type Buildings contract resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingsContractResource extends InstanceResource implements GetEnabled<BuildingsContract>, UpdateEnabled<BuildingsContractUpdateRequest, BuildingsContractUpdateResponse> {

    public BuildingsContractResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Resource represents one building saving product identified by it's identifier. It can be
     * building saving or loan from building saving.
     *
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void get(CallbackWebApi<BuildingsContract> callback) {
        ResourceUtils.callGet(this, null, null, BuildingsContract.class, callback);
    }

    /**
     * Allows to change a limited set of building savings contract-settings of one specific contract.
     * Currently only the field alias can be changed. Change only to alias field must not be signed,
     * but response is ready also for signing process.
     *
     * @param request  the request of type WebApiRequest
     * @param callback the callback of type CallbackWebApi<T>
     */
    @Override
    public void update(BuildingsContractUpdateRequest request, CallbackWebApi<BuildingsContractUpdateResponse> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, BuildingsContractUpdateResponse.class, callback);
    }

    /**
     * Get buildings contracts services resource
     *
     * @return the services resource
     */
    public BuildingsContractsServicesResource getServicesResource() {
        return new BuildingsContractsServicesResource(appendPathWith("services"), getClient());
    }

    /**
     * Get buildings contracts transactions resource
     *
     * @return the transactions resource
     */
    public CzTransactionsResource getTransactionsResource() {
        return new CzTransactionsResource(appendPathWith("transactions"), getClient());
    }
}

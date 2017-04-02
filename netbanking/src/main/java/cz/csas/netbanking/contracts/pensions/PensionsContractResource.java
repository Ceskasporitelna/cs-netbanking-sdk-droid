package cz.csas.netbanking.contracts.pensions;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Pensions contract resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class PensionsContractResource extends InstanceResource implements GetEnabled<Pension>, UpdateEnabled<PensionUpdateRequest, PensionUpdateResponse> {

    public PensionsContractResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Returns detail of pension product which belongs to current user. This can be Pension Saving,
     * Supplementary Pension Insurance and Supplementary Pension Saving.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<Pension> callback) {
        ResourceUtils.callGet(this, null, null, Pension.class, callback);
    }

    /**
     * Allows to change a limited set of pension contract-settings of one specific contract.
     * Currently only the field alias can be changed. Change only to alias field must not be signed,
     * but response is ready also for signing process.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(PensionUpdateRequest request, CallbackWebApi<PensionUpdateResponse> callback) {
        request.setId(getId());
        ResourceUtils.callUpdate(this, request, null, PensionUpdateResponse.class, callback);
    }

    /**
     * Returns transactions resource for pension contract
     *
     * @return the resource
     */
    public TransactionsResource getTransactionsResource() {
        return new TransactionsResource(appendPathWith("transactions"), getClient());
    }
}

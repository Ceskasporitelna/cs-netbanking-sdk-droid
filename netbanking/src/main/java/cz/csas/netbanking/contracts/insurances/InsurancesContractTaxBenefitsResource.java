package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.Resource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.ListEnabled;

/**
 * The type Insurances contract tax benefits resource.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsurancesContractTaxBenefitsResource extends Resource implements GetEnabled<TaxBenefit> {

    public InsurancesContractTaxBenefitsResource(String basePath, WebApiClient client) {
        super(basePath.replace("netbanking/my/", "netbanking/cz/my/"), client);
    }

    /**
     * Returns tax benefits for the life insurance
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<TaxBenefit> callback) {
        ResourceUtils.callGet(this, null, null, TaxBenefit.class, callback);
    }
}

package cz.csas.netbanking;

import cz.csas.cscore.CoreSDK;
import cz.csas.cscore.client.WebApiConfiguration;

/**
 * The type Netbanking.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
class NetbankingImpl extends Netbanking {

    private NetbankingClient netbankingClient;

    @Override
    public Netbanking init(WebApiConfiguration webApiConfiguration) {
        netbankingClient = new NetbankingClient(webApiConfiguration);
        return this;
    }

    @Override
    public NetbankingClient getNetbankingClient() {
        if (netbankingClient == null)
            init();
        return netbankingClient;
    }

    private Netbanking init() {
        WebApiConfiguration webApiConfiguration = CoreSDK.getInstance().getWebApiConfiguration();
        if (webApiConfiguration != null && webApiConfiguration.getWebApiKey() != null && webApiConfiguration.getEnvironment() != null && webApiConfiguration.getEnvironment().getApiContextBaseUrl() != null) {
            netbankingClient = new NetbankingClient(webApiConfiguration);
        } else
            throw new CsNetbankingError(CsNetbankingError.Kind.BAD_INITIALIZATION);
        return this;
    }
}

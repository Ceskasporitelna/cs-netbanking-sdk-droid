package cz.csas.netbanking;

import cz.csas.cscore.CoreSDK;
import cz.csas.cscore.client.WebApiConfiguration;
import cz.csas.cscore.locker.LockerAccessTokenProvider;

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

    private void init() {
        CoreSDK core = CoreSDK.getInstance();
        WebApiConfiguration webApiConfiguration = core.getWebApiConfiguration();
        if (webApiConfiguration != null && webApiConfiguration.getWebApiKey() != null && webApiConfiguration.getEnvironment() != null && webApiConfiguration.getEnvironment().getApiContextBaseUrl() != null) {
            netbankingClient = new NetbankingClient(webApiConfiguration);
            netbankingClient.setAccessTokenProvider(new LockerAccessTokenProvider(core.getLocker(), core.getLogger()));
        } else
            throw new CsNetbankingError(CsNetbankingError.Kind.BAD_INITIALIZATION);
    }
}

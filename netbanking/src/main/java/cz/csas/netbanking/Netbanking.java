package cz.csas.netbanking;

import cz.csas.cscore.client.WebApiConfiguration;

/**
 * The type Netbanking. This object is the core object of NetbankingSDK. It provides NetbankingSDK
 * initialization and NetBanking Client.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public abstract class Netbanking {

    private static Netbanking sharedInstance;

    /**
     * Get instance netbanking.
     *
     * @return the netbanking
     */
    public static Netbanking getInstance() {
        if (sharedInstance == null)
            sharedInstance = new NetbankingImpl();
        return sharedInstance;
    }

    /**
     * Initialize netbanking.
     *
     * @param webApiConfiguration the web api configuration
     * @return the netbanking
     */
    public abstract Netbanking init(WebApiConfiguration webApiConfiguration);

    /**
     * Get netbanking client.
     *
     * @return the netbanking client
     */
    public abstract NetbankingClient getNetbankingClient();
}

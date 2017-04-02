package cz.csas.netbanking.accounts;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.webapi.InstanceResource;
import cz.csas.cscore.webapi.ResourceUtils;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.cscore.webapi.apiquery.GetEnabled;
import cz.csas.cscore.webapi.apiquery.UpdateEnabled;
import cz.csas.netbanking.StatementsResource;
import cz.csas.netbanking.TransactionsResource;

/**
 * The type Account resource. This resource provides Account detail information and data.
 * Also provides following resources:
 * {@link AccountBalanceResource}
 * {@link AccountServicesResource}
 * {@link TransactionsResource}
 * {@link AccountReservationsResource}
 * {@link AccountTransferResource}
 * {@link AccountRepaymentsResource}
 * {@link StatementsResource}
 * {@link SubAccountsResource}
 * {@link AccountStandingOrdersResource}
 * {@link AccountDirectDebitsResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class AccountResource extends InstanceResource implements GetEnabled<MainAccount>,
        UpdateEnabled<ChangeAccountSettingsRequest, ChangeAccountSettingsResponse> {

    /**
     * Instantiates a new Instance resource.
     *
     * @param id       the id
     * @param basePath the base path
     * @param client   the client
     */
    public AccountResource(Object id, String basePath, WebApiClient client) {
        super(id, basePath, client);
    }

    /**
     * Get account defined by this resource.
     *
     * @param callback the callback
     */
    @Override
    public void get(CallbackWebApi<MainAccount> callback) {
        ResourceUtils.callGet(this, null, null, MainAccount.class, callback);
    }

    /**
     * Update Account settings for current account.
     *
     * @param request  the request
     * @param callback the callback
     */
    @Override
    public void update(ChangeAccountSettingsRequest request, CallbackWebApi<ChangeAccountSettingsResponse> callback) {
        ResourceUtils.callUpdate(this, request, null, ChangeAccountSettingsResponse.class, callback);
    }

    /**
     * Get balance resource.
     *
     * @return the balance resource
     */
    public AccountBalanceResource getBalanceResource() {
        return new AccountBalanceResource(appendPathWith("balance"), getClient());
    }

    /**
     * Get services resource.
     *
     * @return the service resource
     */
    public AccountServicesResource getServicesResource() {
        return new AccountServicesResource(appendPathWith("services"), getClient());
    }

    /**
     * Get transactions resource.
     *
     * @return the transactions resource
     */
    public TransactionsResource getTransactionsResource() {
        return new TransactionsResource(appendPathWith("transactions"), getClient());
    }

    /**
     * Get reservations resource.
     *
     * @return the reservations resource
     */
    public AccountReservationsResource getReservationsResource() {
        return new AccountReservationsResource(appendPathWith("reservations"), getClient());
    }

    /**
     * Get transfer resource.
     *
     * @return the transfer resource
     */
    public AccountTransferResource getTransferResource() {
        return new AccountTransferResource(appendPathWith("transfer"), getClient());
    }

    /**
     * Get repayments resource.
     *
     * @return the repayments resource
     */
    public AccountRepaymentsResource getRepaymentsResource() {
        return new AccountRepaymentsResource(appendPathWith("repayments"), getClient());
    }

    /**
     * Get statements resource.
     *
     * @return the statements resource
     */
    public StatementsResource getStatementsResource() {
        return new StatementsResource(appendPathWith("statements"), getClient());
    }

    /**
     * Get sub accounts resource.
     *
     * @return the sub accounts resource
     */
    public SubAccountsResource getSubAccountsResource() {
        return new SubAccountsResource(appendPathWith("subaccounts"), getClient());
    }

    /**
     * Get standing orders resource
     *
     * @return the standing orders resource
     */
    public AccountStandingOrdersResource getStandingOrdersResource() {
        return new AccountStandingOrdersResource(appendPathWith("standingorders"), getClient());
    }

    /**
     * Get direct debits resource
     *
     * @return the direct debits resource
     */
    public AccountDirectDebitsResource getDirectDebitsResource() {
        return new AccountDirectDebitsResource(appendPathWith("directdebits"), getClient());
    }

}

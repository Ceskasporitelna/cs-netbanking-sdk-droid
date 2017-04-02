package cz.csas.netbanking;

import cz.csas.cscore.client.WebApiConfiguration;
import cz.csas.cscore.webapi.WebApiClient;
import cz.csas.netbanking.accounts.AccountsResource;
import cz.csas.netbanking.authorizationLimits.AuthorizationLimitsResource;
import cz.csas.netbanking.authorizationToken.AuthorizationTokenResource;
import cz.csas.netbanking.budgets.BudgetsResource;
import cz.csas.netbanking.bundles.BundlesResource;
import cz.csas.netbanking.cards.CardsResource;
import cz.csas.netbanking.contacts.ContactsResource;
import cz.csas.netbanking.contracts.ContractsResource;
import cz.csas.netbanking.goals.GoalsResource;
import cz.csas.netbanking.messages.MessagesResource;
import cz.csas.netbanking.orders.OrdersResource;
import cz.csas.netbanking.phoneNumbers.PhoneNumbersResource;
import cz.csas.netbanking.plugins.PluginsResource;
import cz.csas.netbanking.profile.ProfileResource;
import cz.csas.netbanking.promotions.PromotionsResource;
import cz.csas.netbanking.securities.SecuritiesResource;
import cz.csas.netbanking.services.ServicesResource;
import cz.csas.netbanking.settings.SettingsResource;
import cz.csas.netbanking.templates.TemplatesResource;

/**
 * The type Netbanking client. This client provides NetbankingSDK resources.
 * <br>
 * Resources:<br>
 * <br>
 * {@link AccountsResource}<br>
 * {@link CardsResource}<br>
 * {@link OrdersResource}<br>
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class NetbankingClient extends WebApiClient {

    /**
     * Instantiates a new Netbanking client.
     *
     * @param webApiConfiguration the cs configuration
     */
    public NetbankingClient(WebApiConfiguration webApiConfiguration) {
        super(webApiConfiguration);
    }

    @Override
    protected String getClientPath() {
        return "/api/v3";
    }

    private String getBasePath() {
        return "netbanking/my";
    }

    /**
     * Get the accounts resource.
     *
     * @return the account resource
     */
    public AccountsResource getAccountsResource() {
        return new AccountsResource(getBasePath() + "/accounts", this);
    }

    /**
     * Get the cards resource.
     *
     * @return the cards resource
     */
    public CardsResource getCardsResource() {
        return new CardsResource(getBasePath() + "/cards", this);
    }

    /**
     * Get the orders resource.
     *
     * @return the orders resource
     */
    public OrdersResource getOrdersResource() {
        return new OrdersResource(getBasePath() + "/orders", this);
    }

    /**
     * Get profile resource.
     *
     * @return the profile resource
     */
    public ProfileResource getProfileResource() {
        return new ProfileResource(getBasePath() + "/profile", this);
    }

    /**
     * Get contracts resource.
     *
     * @return the contracts resource
     */
    public ContractsResource getContractsResource() {
        return new ContractsResource(getBasePath() + "/contracts", this);
    }


    /**
     * Get securities resource.
     *
     * @return the securities resource
     */
    public SecuritiesResource getSecuritiesResource() {
        return new SecuritiesResource(getBasePath() + "/securities", this);
    }

    /**
     * Get services resource.
     *
     * @return the services resource
     */
    public ServicesResource getServicesResource() {
        return new ServicesResource(getBasePath() + "/services", this);
    }

    /**
     * Get phone numbers resource.
     *
     * @return the phone numbers resource
     */
    public PhoneNumbersResource getPhoneNumbersResource() {
        return new PhoneNumbersResource(getBasePath() + "/phone-numbers", this);
    }

    /**
     * Get settings resource.
     *
     * @return the settings resource
     */
    public SettingsResource getSettingsResource() {
        return new SettingsResource(getBasePath() + "/settings", this);
    }

    /**
     * Get contacts resource.
     *
     * @return the contacts resource
     */
    public ContactsResource getContactsResource() {
        return new ContactsResource(getBasePath() + "/contacts", this);
    }

    /**
     * Get messages resource.
     *
     * @return the messages resource
     */
    public MessagesResource getMessagesResource() {
        return new MessagesResource(getBasePath() + "/messages", this);
    }

    /**
     * Get promotions resource.
     *
     * @return the promotions resource
     */
    public PromotionsResource getPromotionsResource() {
        return new PromotionsResource(getBasePath() + "/promotions", this);
    }

    /**
     * Get templates resource.
     *
     * @return the templates resource
     */
    public TemplatesResource getTemplatesResource() {
        return new TemplatesResource(getBasePath() + "/templates", this);
    }

    /**
     * Get plugins resource.
     *
     * @return the plugins resource
     */
    public PluginsResource getPluginsResource() {
        return new PluginsResource(getBasePath() + "/plugins", this);
    }

    /**
     * Get budgets resource.
     *
     * @return the budgets resource
     */
    public BudgetsResource getBudgetsResource() {
        return new BudgetsResource(getBasePath() + "/budgets", this);
    }

    /**
     * Get goals resource.
     *
     * @return the goals resource
     */
    public GoalsResource getGoalsResource() {
        return new GoalsResource(getBasePath() + "/goals", this);
    }

    /**
     * Get authorization limits resource.
     *
     * @return the authorization limits resource
     */
    public AuthorizationLimitsResource getAuthorizationLimitsResource() {
        return new AuthorizationLimitsResource(getBasePath() + "/authorizationLimits", this);
    }

    /**
     * Get auth resource.
     *
     * @return the auth resource
     */
    public AuthorizationTokenResource getAuthorizationTokenResource() {
        return new AuthorizationTokenResource(getBasePath() + "/auth/token", this);
    }

    /**
     * Get bundles resource.
     *
     * @return the bundles resource
     */
    public BundlesResource getBundlesResource() {
        return new BundlesResource(getBasePath() + "/bundles", this);
    }

}

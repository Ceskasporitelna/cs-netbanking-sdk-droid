package cz.csas.netbanking.securities;

import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.CzTransactionsResource;

/**
 * The type Security.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Security extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String accountno;

    @CsExpose
    private String alias;

    @CsExpose
    private String description;

    @CsExpose
    private Amount balance;

    @CsExpose
    private List<SubSecAccount> subSecAccounts;

    /**
     * Product id
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Account identification number of security portfolio (MUIN)
     *
     * @return the accountno
     */
    public String getAccountno() {
        return accountno;
    }

    /**
     * Alias for security portfolio. Max. 50 characters.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Description - Securities portfolio Account name, Name of principal account holder
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Account balance value
     *
     * @return the balance
     */
    public Amount getBalance() {
        return balance;
    }

    /**
     * Array of securities sub accounts
     *
     * @return the sub sec accounts
     */
    public List<SubSecAccount> getSubSecAccounts() {
        return subSecAccounts;
    }

    /**
     * Convenience getter for getting security's transactions resource
     *
     * @return the security transactions resource
     */
    public CzTransactionsResource getSecurityTransactionsResource() {
        if (resource instanceof SecurityResource)
            return ((SecurityResource) resource).getTransactionsResource();
        return null;
    }

    /**
     * Convenience method for getting security detail right from the list
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<Security> callback) {
        if (resource instanceof SecurityResource)
            ((SecurityResource) resource).get(callback);
    }

    /**
     * Convenience method for updating security's details
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(SecurityUpdateRequest request, CallbackWebApi<SecurityUpdateResponse> callback) {
        if (resource instanceof SecurityResource)
            ((SecurityResource) resource).update(request, callback);
    }
}

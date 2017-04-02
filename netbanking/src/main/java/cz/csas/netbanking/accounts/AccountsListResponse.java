package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Accounts list response provides list of Accounts.
 */
public class AccountsListResponse extends PaginatedListResponse<MainAccount, AccountsListResponse> {

    @CsExpose
    @CsSerializedName(value = "accounts", alternate = "items")
    private List<MainAccount> accounts;

    /**
     * Get list of Accounts
     *
     * @return the list of accounts
     */
    @Override
    protected List<MainAccount> getItems() {
        return accounts;
    }

    /**
     * Get list of accounts.
     * Convenience method for {@link #getItems()}
     *
     * @return the list of accounts
     */
    public List<MainAccount> getAccounts() {
        return accounts;
    }
}

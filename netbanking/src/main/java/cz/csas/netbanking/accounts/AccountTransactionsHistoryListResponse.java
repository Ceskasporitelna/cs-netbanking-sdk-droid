package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 22/01/2018.
 */
public class AccountTransactionsHistoryListResponse extends PaginatedListResponse<AccountTransaction, AccountTransactionsHistoryListResponse> {

    @CsExpose
    @CsSerializedName(value = "transactions", alternate = "items")
    private List<AccountTransaction> accountTransactions;


    @Override
    protected List<AccountTransaction> getItems() {
        return accountTransactions;
    }

    public List<AccountTransaction> getAccountTransactions() {
        return accountTransactions;
    }
}

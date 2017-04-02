package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.webapi.Sort;
import cz.csas.cscore.webapi.SortDirection;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Accounts list test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.list";
    private CountDownLatch mAccountsSignal;
    private AccountsListResponse mAccountsListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mAccountsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test accounts list.
     */
    @Test
    public void testAccountsList() {
        AccountsParameters parameters = new AccountsParameters(null,
                Sort.by(AccountsSortableFields.IBAN, SortDirection.ASCENDING)
                .thenBy(AccountsSortableFields.BALANCE, SortDirection.DESCENDING), ProductType.CURRENT);

        mNetbankingClient.getAccountsResource().list(parameters, new CallbackWebApi<AccountsListResponse>() {
            @Override
            public void success(AccountsListResponse accountsListResponse) {
                mAccountsListResponse = accountsListResponse;
                mAccountsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mAccountsSignal.countDown();
            }
        });

        try {
            mAccountsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<MainAccount> accounts = mAccountsListResponse.getAccounts();
        assertEquals(Long.valueOf(0), mAccountsListResponse.getPageNumber());
        assertEquals(Long.valueOf(1), mAccountsListResponse.getPageCount());
        assertEquals(Long.valueOf(1), mAccountsListResponse.getPageSize());
        assertEquals(1, accounts.size());

        MainAccount account = accounts.get(0);
        AccountNumber number = account.getAccountno();
        Amount balance = account.getBalance();
        Amount disposable = account.getDisposable();

        assertEquals("076E1DBCCCD38729A99D93AC8D3E8273237C7E36", account.getId());
        assertEquals("2328489013", number.getNumber());
        assertEquals("0800", number.getBankCode());
        assertEquals("CZ", number.getCountryCode());
        assertEquals("CZ5908000000002328489013", number.getCzIban());
        assertEquals("GIBACZPX", number.getCzBic());
        assertEquals(Long.valueOf(2650706), balance.getValue());
        assertEquals(Integer.valueOf(2), balance.getPrecision());
        assertEquals("CZK", balance.getCurrency());
        assertEquals("49", account.getProduct());
        assertEquals("Osobní účet ČS II", account.getProductI18N());
        assertEquals(ProductType.CURRENT, account.getType());
        assertEquals(ProductSubType.GIRO_ACCOUNT, account.getSubtype());
        assertEquals(Long.valueOf(2650706), disposable.getValue());
        assertEquals(Integer.valueOf(2), disposable.getPrecision());
        assertEquals("CZK", disposable.getCurrency());

        assertEquals(Arrays.asList(
                "owner",
                "electronicStatementAllowed",
                "accountQueryAllowed",
                "directDebitAllowed",
                "sipoDirectDebitAllowed",
                "ownTransferAllowed",
                "domesticTransferAllowed",
                "urgentTransferAllowed"
        ), account.getFlags());

        assertEquals("Anna Vojtíšková", account.getDescription());
    }
}

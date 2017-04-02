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
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Accounts list page 0 test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountsListPage0Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.list.page0";
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
     * Test accounts list page 0.
     */
    @Test
    public void testAccountsListPage0() {
        AccountsParameters parameters = new AccountsParameters(new Pagination(0, 1));

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
        assertEquals(Long.valueOf(2), mAccountsListResponse.getPageCount());
        assertEquals(Long.valueOf(1), mAccountsListResponse.getPageSize());
        assertEquals(Long.valueOf(1), mAccountsListResponse.getNextPage());
        assertEquals(1, accounts.size());

        MainAccount account = accounts.get(0);
        AccountNumber number = account.getAccountno();
        Amount balance = account.getBalance();
        Amount disposable = account.getDisposable();

        assertEquals("4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE", account.getId());
        assertEquals("2059930033", number.getNumber());
        assertEquals("0800", number.getBankCode());
        assertEquals("CZ", number.getCountryCode());
        assertEquals("CZ1208000000002059930033", number.getCzIban());
        assertEquals("GIBACZPX", number.getCzBic());
        assertEquals(Long.valueOf(1243017), balance.getValue());
        assertEquals(Integer.valueOf(2), balance.getPrecision());
        assertEquals("CZK", balance.getCurrency());
        assertEquals("50", account.getProduct());
        assertEquals("Osobní konto ČS", account.getProductI18N());
        assertEquals(ProductType.CURRENT, account.getType());
        assertEquals(ProductSubType.GIRO_ACCOUNT, account.getSubtype());
        assertEquals(Long.valueOf(1243017), disposable.getValue());
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

        assertEquals("Aleš Vrba", account.getDescription());
        assertEquals("muj ucet", account.getAlias());

        List<SubAccount> subAccounts = account.getSubaccounts();
        assertEquals(1, subAccounts.size());
        SubAccount subAccount = subAccounts.get(0);
        AccountNumber subNumber = subAccount.getAccountno();
        Amount subBalance = subAccount.getBalance();
        Amount interestRateLimit = subAccount.getCzInterestRateLimit();

        assertEquals("932933BABDE2A94753BAFF7FF146BA69BA90C259", subAccount.getId());
        assertEquals("3668601379", subNumber.getNumber());
        assertEquals("0800", subNumber.getBankCode());
        assertEquals("CZ", subNumber.getCountryCode());
        assertEquals("CZ7308000000003668601379", subNumber.getCzIban());
        assertEquals("GIBACZPX", subNumber.getCzBic());
        assertEquals(Long.valueOf(1607876), subBalance.getValue());
        assertEquals(Integer.valueOf(2), subBalance.getPrecision());
        assertEquals("CZK", subBalance.getCurrency());
        assertEquals("53", subAccount.getProduct());
        assertEquals("Spořicí účet k Osobnímu kontu", subAccount.getProductI18N());
        assertEquals(ProductType.SAVING, subAccount.getType());
        assertEquals(ProductSubType.SAVING_ACCOUNT, subAccount.getSubtype());
        assertEquals(Double.valueOf(0.4), subAccount.getCreditInterestRate());
        assertEquals(Double.valueOf(0.01), subAccount.getCzInterestRateOverLimit());
        assertEquals(Long.valueOf(15000000), interestRateLimit.getValue());
        assertEquals(Integer.valueOf(2), interestRateLimit.getPrecision());
        assertEquals("CZK", interestRateLimit.getCurrency());
    }
}

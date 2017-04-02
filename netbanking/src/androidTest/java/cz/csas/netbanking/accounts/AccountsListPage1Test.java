package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.sql.Date;
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
 * The type Accounts list page 1 test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.list.page1";
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
     * Test accounts list page 1.
     */
    @Test
    public void testAccountsListPage1() {
        AccountsParameters parameters = new AccountsParameters(new Pagination(1, 1));

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
        assertEquals(Long.valueOf(1), mAccountsListResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mAccountsListResponse.getPageCount());
        assertEquals(Long.valueOf(1), mAccountsListResponse.getPageSize());
        assertEquals(1, accounts.size());

        MainAccount account = accounts.get(0);
        AccountNumber number = account.getAccountno();
        Amount balance = account.getBalance();
        Loan loan = account.getLoan();

        assertEquals("EC1C13B722F726D783365D0A89D23E805098B167", account.getId());
        assertEquals("428602109", number.getNumber());
        assertEquals("0800", number.getBankCode());
        assertEquals("CZ", number.getCountryCode());
        assertEquals("CZ6408000000000428602109", number.getCzIban());
        assertEquals("GIBACZPX", number.getCzBic());
        assertEquals(Long.valueOf(83492030), balance.getValue());
        assertEquals(Integer.valueOf(2), balance.getPrecision());
        assertEquals("CZK", balance.getCurrency());
        assertEquals("15", account.getProduct());
        assertEquals("Hypotéka České spořitelny", account.getProductI18N());
        assertEquals(ProductType.LOAN, account.getType());
        assertEquals(ProductSubType.MORTGAGE, account.getSubtype());

        assertEquals(Long.valueOf(90200000), loan.getLoanAmount().getValue());
        assertEquals(Integer.valueOf(2), loan.getLoanAmount().getPrecision());
        assertEquals("CZK", loan.getLoanAmount().getCurrency());
        assertEquals(Date.valueOf("2037-12-31"), loan.getMaturityDate());
        assertEquals(Long.valueOf(0), loan.getRemainingLoanAmount().getValue());
        assertEquals(Integer.valueOf(2), loan.getRemainingLoanAmount().getPrecision());
        assertEquals("CZK", loan.getRemainingLoanAmount().getCurrency());
        assertEquals(Date.valueOf("2012-12-31"), loan.getDrawdownToDate());
        assertEquals(Long.valueOf(83492030), loan.getDrawdownAmount().getValue());
        assertEquals(Integer.valueOf(2), loan.getDrawdownAmount().getPrecision());
        assertEquals("CZK", loan.getDrawdownAmount().getCurrency());
        assertEquals(Long.valueOf(83492030), loan.getOutstandingDebt().getValue());
        assertEquals(Integer.valueOf(2), loan.getOutstandingDebt().getPrecision());
        assertEquals("CZK", loan.getOutstandingDebt().getCurrency());
        assertEquals(InstallmentFrequency.MONTHLY, loan.getInstallmentFrequency());
        assertEquals(Integer.valueOf(31), loan.getInstallmentDay());
        assertEquals(Date.valueOf("2016-03-31"), loan.getNextRateDate());
        assertEquals(Long.valueOf(429300), loan.getNextRateAmount().getValue());
        assertEquals(Integer.valueOf(2), loan.getNextRateAmount().getPrecision());
        assertEquals("CZK", loan.getNextRateAmount().getCurrency());
        assertEquals(Long.valueOf(84407467), loan.getCzLumpsumRepayment().getValue());
        assertEquals(Integer.valueOf(2), loan.getCzLumpsumRepayment().getPrecision());
        assertEquals("CZK", loan.getCzLumpsumRepayment().getCurrency());

        assertEquals(Double.valueOf(2.99), account.getDebitInterestRate());

        assertEquals(Arrays.asList(
                "owner",
                "electronicStatementAllowed",
                "accountQueryAllowed",
                "standingOrderNotAllowed"
        ), account.getFlags());

        assertEquals("Aleš Vrba", account.getDescription());
    }
}

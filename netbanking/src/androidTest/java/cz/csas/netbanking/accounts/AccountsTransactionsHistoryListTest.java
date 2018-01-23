package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

/**
 * The type Accounts list test.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 22 /01/2018.
 */
public class AccountsTransactionsHistoryListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.transactionsHistory.list";
    private CountDownLatch mAccountsTransactionsHistorySignal;
    private AccountTransactionsListResponse mAccountTransactionsListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mAccountsTransactionsHistorySignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testAccountTransactionsHistoryList() {

        AccountTransactionsParameters parameters = new AccountTransactionsParameters.Builder()
                .setDateStart(TimeUtils.getISO8601Date("2014-06-01T00:00:00+02:00"))
                .setDateEnd(TimeUtils.getISO8601Date("2014-06-30T00:00:00+02:00"))
                .setPagination(new Pagination(0, 2))
                .build();

        mNetbankingClient.getAccountsResource().withId("CZ5508000000000379554193").getTransactionsHistoryResource().list(parameters, new CallbackWebApi<AccountTransactionsListResponse>() {
            @Override
            public void success(AccountTransactionsListResponse accountTransactionsListResponse) {
                mAccountTransactionsListResponse = accountTransactionsListResponse;
                mAccountsTransactionsHistorySignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mAccountsTransactionsHistorySignal.countDown();
            }
        });

        try {
            mAccountsTransactionsHistorySignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<AccountTransaction> transactions = mAccountTransactionsListResponse.getAccountTransactions();
        for (AccountTransaction transaction : transactions) {
            AccountParty accountParty = transaction.getAccountParty();
            Amount amount = transaction.getAmount();
            Amount amountSender = transaction.getAmountSender();
            Long amountValue = null;
            switch (transaction.getId()) {
                case "I141126DXHZ3T":
                    amountValue = 100L;
                    break;
                case "I141126DXHZ3U":
                    amountValue = 200L;
                    break;
            }
            assertEquals(accountParty.getAccountNumber(), "2812275553");
            assertEquals(accountParty.getAccountPrefix(), "0");
            assertEquals(accountParty.getBankCode(), "0800");
            assertEquals(accountParty.getBic(), "GIBACZPX");
            assertEquals(accountParty.getIban(), "CZ2908000000002812275553");
            assertEquals(accountParty.getPartyInfo(), "Petr Malý");
            assertEquals(accountParty.getPartyDescription(), "2812275553/0800");
            assertEquals(amount.getCurrency(), "CZK");
            assertEquals(amount.getPrecision(), Integer.valueOf(2));
            assertEquals(amount.getValue(), amountValue);
            assertEquals(amountSender.getCurrency(), "CZK");
            assertEquals(amountSender.getPrecision(), Integer.valueOf(2));
            assertEquals(amountSender.getValue(), amountValue);
            assertEquals(transaction.getBookingDate(), TimeUtils.getISO8601Date("2014-11-26T00:00:00+01:00"));
            assertEquals(transaction.getCardNumber(), Integer.valueOf(0));
            assertEquals(transaction.getConstantSymbol(), "0558");
            assertEquals(transaction.getDescription(), "domácí platba");
            assertFalse(transaction.getDescriptionEditable());
            assertEquals(transaction.getPayeeNote(), "note for payee");
            assertEquals(transaction.getPayerNote(), "note for payer");
            assertEquals(transaction.getSpecificSymbol(), "55");
            assertEquals(transaction.getTransactionType(), "54");
            assertEquals(transaction.getValuationDate(), TimeUtils.getISO8601Date("2014-11-26T00:00:00+01:00"));
            assertEquals(transaction.getVariableSymbol(), "0000000009");
        }
    }
}

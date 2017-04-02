package cz.csas.netbanking.accounts;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.error.CsSDKError;
import cz.csas.cscore.judge.Constants;
import cz.csas.cscore.judge.JudgeUtils;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Accounts with balances test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountWithIdBalancesTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.balances.get";
    private CountDownLatch mAccountsSignal;
    private AccountBalance mBalance;

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
     * Test account with ID balances.
     */
    @Test
    public void testAccountWithIdBalances() {

        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        mNetbankingClient.getAccountsResource().withId(accountId).getBalanceResource().get(new CallbackWebApi<AccountBalance>() {
            @Override
            public void success(AccountBalance accountBalance) {
                mBalance = accountBalance;
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
        
        Amount balance = mBalance.getBalance();
        Amount disposable = mBalance.getDisposable();

        assertEquals(Long.valueOf(2650706), balance.getValue());
        assertEquals(Integer.valueOf(2), balance.getPrecision());
        assertEquals("CZK", balance.getCurrency());
        assertEquals(Long.valueOf(2650706), disposable.getValue());
        assertEquals(Integer.valueOf(2), disposable.getPrecision());
        assertEquals("CZK", disposable.getCurrency());
    }
}

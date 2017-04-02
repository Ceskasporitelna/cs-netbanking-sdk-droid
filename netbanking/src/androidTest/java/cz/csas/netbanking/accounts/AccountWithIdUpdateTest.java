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
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Account with ID update test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class AccountWithIdUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.update";
    private CountDownLatch mAccountsSignal;
    private ChangeAccountSettingsResponse mAccount;

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
     * Test account with ID update.
     */
    @Test
    public void testAccountWithIdUpdate() {

        String id = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        String alias = "muj ucet";

        ChangeAccountSettingsRequest request = new ChangeAccountSettingsRequest(alias);
        mNetbankingClient.getAccountsResource().withId(id).update(request, new CallbackWebApi<ChangeAccountSettingsResponse>() {
            @Override
            public void success(ChangeAccountSettingsResponse mainAccount) {
                mAccount = mainAccount;
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

        AccountNumber number = mAccount.getAccountno();
        Amount balance = mAccount.getBalance();
        Amount disposable = mAccount.getDisposable();

        assertEquals(id, mAccount.getId());
        assertEquals("2059930033", number.getNumber());
        assertEquals("0800", number.getBankCode());
        assertEquals("CZ", number.getCountryCode());
        assertEquals("CZ1208000000002059930033", number.getCzIban());
        assertEquals("GIBACZPX", number.getCzBic());
        assertEquals(Long.valueOf(1208017), balance.getValue());
        assertEquals(Integer.valueOf(2), balance.getPrecision());
        assertEquals("CZK", balance.getCurrency());
        assertEquals("50", mAccount.getProduct());
        assertEquals("Osobní konto ČS", mAccount.getProductI18N());
        assertEquals(ProductType.CURRENT, mAccount.getType());
        assertEquals(ProductSubType.GIRO_ACCOUNT, mAccount.getSubtype());
        assertEquals(Long.valueOf(1208017), disposable.getValue());
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
        ), mAccount.getFlags());

        assertEquals("Aleš Vrba", mAccount.getDescription());
        assertEquals("muj ucet", mAccount.getAlias());
        assertEquals(SigningState.NONE, mAccount.signing().getSigningState());

        List<SubAccount> subAccounts = mAccount.getSubaccounts();
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

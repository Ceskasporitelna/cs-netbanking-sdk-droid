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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.signing.SigningState;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.orders.Symbols;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class DirectDebitsWithIdCreateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.directDebts.create";
    private CountDownLatch mCountDownLatch;
    private DirectDebitResponse mResponse;

    @Override
    public void setUp() {
        super.setUp();
        mCountDownLatch = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    @Test
    public void testDirectDebitsWithIdDelete() {
        String accountId = "4B2F9EBE742BCAE1E98A78E12F6FBC62464A74EE";

        DirectDebitCreateRequest request = new DirectDebitCreateRequest.Builder()
                .setType(DirectDebitType.DIRECT_DEBIT)
                .setReceiver(new AccountNumber("428602109", "0800"))
                .setAlias("moje inkaso")
                .setPeriodicity(1)
                .setPeriodCycle(PeriodCycle.MONTHLY)
                .setLimit(new Amount(100000l, 2, "CZK"))
                .setLimitSum(new Amount(300000l, 2, "CZK"))
                .setNumberLimit(5)
                .setStartDate(TimeUtils.getPlainDate("2017-07-14"))
                .setEndDate(TimeUtils.getPlainDate("2018-07-14"))
                .setSymbols(new Symbols("4567", null, "800"))
                .build();

        mNetbankingClient.getAccountsResource().withId(accountId).getDirectDebitsResource()
                .create(request, new CallbackWebApi<DirectDebitResponse>() {
            @Override
            public void success(DirectDebitResponse directDebit) {
                mResponse = directDebit;
                mCountDownLatch.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mCountDownLatch.countDown();
            }
        });

        try {
            mCountDownLatch.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DirectDebitResponse order = mResponse;
        AccountNumber receiver = order.getReceiver();
        Symbols symbols = order.getSymbols();
        Amount limit = order.getLimit();
        Amount limitSum = order.getLimitSum();

        assertEquals("428602109", receiver.getNumber());
        assertEquals("0800", receiver.getBankCode());

        assertEquals(DirectDebitType.DIRECT_DEBIT, order.getType());
        assertEquals("moje inkaso", order.getAlias());
        assertEquals(Integer.valueOf(1), order.getPeriodicity());
        assertEquals(PeriodCycle.MONTHLY, order.getPeriodCycle());

        assertEquals(Long.valueOf(100000l), limit.getValue());
        assertEquals(Integer.valueOf(2), limit.getPrecision());
        assertEquals("CZK", limit.getCurrency());

        assertEquals(Long.valueOf(300000l), limitSum.getValue());
        assertEquals(Integer.valueOf(2), limitSum.getPrecision());
        assertEquals("CZK", limitSum.getCurrency());

        assertEquals(TimeUtils.getPlainDate("2017-07-14"), order.getStartDate());
        assertEquals(TimeUtils.getPlainDate("2018-07-14"), order.getEndDate());

        assertEquals("4567", symbols.getVariableSymbol());
        assertEquals("800", symbols.getSpecificSymbol());

        assertEquals(SigningState.OPEN, order.signing().getSigningState());
        assertEquals("160530104689642", order.signing().getSignId());
    }
}

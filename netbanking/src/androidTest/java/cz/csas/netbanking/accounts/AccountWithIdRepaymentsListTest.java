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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Account with id repayments list test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.04.16.
 */
public class AccountWithIdRepaymentsListTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "accounts.withId.repayments.list";
    private CountDownLatch mRepaymentsSignal;
    private RepaymentsListResponse mRepaymentsListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mRepaymentsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account with id repayments.
     */
    @Test
    public void testAccountWithIdRepayments() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";

        mNetbankingClient.getAccountsResource().withId(accountId).getRepaymentsResource().list(new CallbackWebApi<RepaymentsListResponse>() {
            @Override
            public void success(RepaymentsListResponse repaymentsListResponse) {
                mRepaymentsListResponse = repaymentsListResponse;
                mRepaymentsSignal.countDown();
            }

            @Override
            public void failure(CsSDKError error) {
                mRepaymentsSignal.countDown();
            }
        });

        try {
            mRepaymentsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Repayment> repayments = mRepaymentsListResponse.getRepayments();
        assertEquals(2, repayments.size());

        for (int i = 0; i < repayments.size(); ++i) {
            Repayment repayment = repayments.get(i);
            switch (i) {
                case 0:
                    assertEquals(TimeUtils.getPlainDate("2016-01-18"), repayment.getRepaymentDate());
                    assertEquals(Long.valueOf(32500), repayment.getAmount().getValue());
                    assertEquals(Integer.valueOf(2), repayment.getAmount().getPrecision());
                    assertEquals("CZK", repayment.getAmount().getCurrency());
                    assertEquals(Long.valueOf(32500), repayment.getPaidAmount().getValue());
                    assertEquals(Integer.valueOf(2), repayment.getPaidAmount().getPrecision());
                    assertEquals("CZK", repayment.getPaidAmount().getCurrency());
                    break;
                case 1:
                    assertEquals(TimeUtils.getPlainDate("2016-02-18"), repayment.getRepaymentDate());
                    assertEquals(Long.valueOf(32500), repayment.getAmount().getValue());
                    assertEquals(Integer.valueOf(2), repayment.getAmount().getPrecision());
                    assertEquals("CZK", repayment.getAmount().getCurrency());
                    assertEquals(Long.valueOf(25000), repayment.getPaidAmount().getValue());
                    assertEquals(Integer.valueOf(2), repayment.getPaidAmount().getPrecision());
                    assertEquals("CZK", repayment.getPaidAmount().getCurrency());
                    break;
            }
        }
    }


}

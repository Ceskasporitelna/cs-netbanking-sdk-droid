package cz.csas.netbanking.budgets;

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
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class BudgetsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "budgets.list";
    private CountDownLatch mCountDownLatch;
    private BudgetsListResponse mResponse;

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
    public void testBudgetsList() {
        mNetbankingClient.getBudgetsResource().list(
                new CallbackWebApi<BudgetsListResponse>() {
                    @Override
                    public void success(BudgetsListResponse budgetsListResponse) {
                        mResponse = budgetsListResponse;
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

        List<Budget> categories = mResponse.getBudgets();
        assertEquals(3, categories.size());
        assertEquals(Boolean.valueOf(true), mResponse.getManuallySet());

        for (int i = 0; i < categories.size(); i++) {
            Budget budget = categories.get(i);
            Category category = budget.getCategory();
            Amount amount = budget.getBudget();
            switch (i) {
                case 0:
                    assertEquals(CategoryId.CAR, category.getId());
                    assertEquals("mainCategory", category.getLevel());
                    assertEquals(Long.valueOf(2000000), amount.getValue());
                    assertEquals(Integer.valueOf(2), amount.getPrecision());
                    assertEquals("CZK", amount.getCurrency());
                    break;
                case 1:
                    assertEquals(CategoryId.WITHDRAWAL, category.getId());
                    assertEquals("mainCategory", category.getLevel());
                    assertNull(amount);
                    break;
                case 2:
                    assertEquals(CategoryId.CLOTHING, category.getId());
                    assertEquals("mainCategory", category.getLevel());
                    assertNull(amount);
                    break;
            }
        }
    }
}

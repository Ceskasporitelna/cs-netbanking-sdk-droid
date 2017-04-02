package cz.csas.netbanking.budgets;

import org.junit.Test;

import java.util.Arrays;
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
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 05.09.16.
 */
public class BudgetsUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "budgets.update";
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
    public void testBudgetsUpdate()
    {
        Budget requestCategory = new Budget(
                new Category(CategoryId.OTHER_EXPENSES, "mainCategory"),
                new Amount(5000l, 2, "CZK"));
        BudgetsUpdateRequest request = new BudgetsUpdateRequest(Arrays.asList(requestCategory));
        mNetbankingClient.getBudgetsResource().update(request, new CallbackWebApi<BudgetsListResponse>() {
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

        assertEquals(1, mResponse.getItems().size());
        Budget budget = mResponse.getItems().get(0);
        Category category = budget.getCategory();
        Amount amount = budget.getBudget();

        assertEquals(CategoryId.OTHER_EXPENSES, category.getId());
        assertEquals("mainCategory", category.getLevel());
        assertEquals(Long.valueOf(5000), amount.getValue());
        assertEquals(Integer.valueOf(2), amount.getPrecision());
        assertEquals("CZK", amount.getCurrency());
    }

}

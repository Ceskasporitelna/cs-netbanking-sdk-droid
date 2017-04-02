package cz.csas.netbanking.phoneNumbers;

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class PhoneNumbersUpdateTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "phoneBook.withId.update";
    private CountDownLatch mCountDownLatch;
    private PhoneNumber mResponse;

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
    public void testPhoneNumberUpdate() {
        PhoneNumberUpdateRequest request = new PhoneNumberUpdateRequest("Graham B.", "777952341", Arrays.asList("isFavourite"));
        mNetbankingClient.getPhoneNumbersResource().withId("2195").update(request, new CallbackWebApi<PhoneNumber>() {
            @Override
            public void success(PhoneNumber phoneNumber) {
                mResponse = phoneNumber;
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

        PhoneNumber phoneNumber = mResponse;
        assertEquals("2195", phoneNumber.getId());
        assertEquals("Graham B.", phoneNumber.getAlias());
        assertEquals("777952341", phoneNumber.getPhoneNumber());
        assertEquals(Arrays.asList("isFavourite"), phoneNumber.getFlags());
    }
}

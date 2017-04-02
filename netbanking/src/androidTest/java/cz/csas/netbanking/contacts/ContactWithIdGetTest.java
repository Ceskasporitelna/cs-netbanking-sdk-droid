package cz.csas.netbanking.contacts;

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
import cz.csas.netbanking.Address;
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.settings.SettingsUpdateResponse;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class ContactWithIdGetTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "contacts.withId.get";
    private CountDownLatch mCountDownLatch;
    private Contact mResponse;

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
    public void testContactsWithIdGet() {
        String id = "postaladdresspermanent";
        mNetbankingClient.getContactsResource().withId(id).get(new CallbackWebApi<Contact>() {
            @Override
            public void success(Contact contact) {
                mResponse = contact;
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

        Contact contact = mResponse;
        assertEquals("postaladdresspermanent", contact.getId());
        assertEquals(ContactType.ADDRESS, contact.getType());
        assertEquals(Arrays.asList("mainContact"), contact.getFlags());
        ContactAddress address = contact.getAddress();
        assertEquals(ContactAddressType.PERMANENT_RESIDENCE, address.getType());
        assertEquals( "Trvalá adresa", address.getTypeI18N());
        assertEquals("CZ", address.getCountry());
        assertEquals("Rakovník", address.getCity());
        assertEquals("Pod Václavem", address.getStreet());
        assertEquals("2092", address.getBuildingApartment());
        assertEquals("26901", address.getZipCode());
    }
}

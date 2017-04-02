package cz.csas.netbanking.contacts;

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
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 01.09.16.
 */
public class ContactsListTest extends NetbankingTest {

    private final String X_JUDGE_CASE = "contacts.list";
    private CountDownLatch mCountDownLatch;
    private ContactsListResponse mResponse;

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
    public void testContactsList() {
        String id = "postaladdresspermanent";
        mNetbankingClient.getContactsResource().list(new CallbackWebApi<ContactsListResponse>() {
            @Override
            public void success(ContactsListResponse contactsListResponse) {
                mResponse = contactsListResponse;
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

        List<Contact> contacts = mResponse.getContacts();
        assertEquals(2, contacts.size());

        for(int i=0; i<contacts.size(); ++i) {
            Contact contact = contacts.get(i);
            ContactAddress address = contact.getAddress();
            ContactPhone phone = contact.getPhone();
            switch (i) {
                case 0:
                    assertEquals("postaladdresspermanent", contact.getId());
                    assertEquals(ContactType.ADDRESS, contact.getType());
                    assertEquals(Arrays.asList("mainContact"), contact.getFlags());

                    assertEquals(ContactAddressType.PERMANENT_RESIDENCE, address.getType());
                    assertEquals( "Trvalá adresa", address.getTypeI18N());
                    assertEquals("CZ", address.getCountry());
                    assertEquals("Rakovník", address.getCity());
                    assertEquals("Pod Václavem", address.getStreet());
                    assertEquals("2092", address.getBuildingApartment());
                    assertEquals("26901", address.getZipCode());
                    break;
                case 1:
                    assertEquals("phonenumberprimary", contact.getId());
                    assertEquals(ContactType.PHONE, contact.getType());
                    assertEquals(Arrays.asList("mainContact"), contact.getFlags());

                    assertEquals(ContactPhoneType.PRIVATE, phone.getType());
                    assertEquals("Telefonní číslo", phone.getTypeI18N());
                    assertEquals("+420", phone.getCountryCallingCode());
                    assertEquals("722736507", phone.getPhoneNumber());
                    break;
            }
        }

    }
}

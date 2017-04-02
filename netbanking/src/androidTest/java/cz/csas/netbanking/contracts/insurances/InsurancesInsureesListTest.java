package cz.csas.netbanking.contracts.insurances;

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
import cz.csas.netbanking.NetbankingTest;
import cz.csas.netbanking.contacts.ContactAddress;
import cz.csas.netbanking.contacts.ContactAddressType;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsurancesInsureesListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.insurees.list";
    private CountDownLatch mCountDownLatch;
    private InsureesListResponse mResponse;

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
    public void testInsureesList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";
        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getInsureesResource().list(new CallbackWebApi<InsureesListResponse>() {
            @Override
            public void success(InsureesListResponse insureesListResponse) {
                mResponse = insureesListResponse;
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

        List<Insuree> insurees = mResponse.getInsurees();
        assertEquals(1, insurees.size());

        Insuree insuree = insurees.get(0);
        assertEquals("78afefe2d55e124cbd4a1bbfa1a1bbb0b1ec5bc8b434a2a17703ea6c6d597092", insuree.getId());
        assertEquals(InsureeType.POLICY_HOLDER, insuree.getType());
        assertEquals("Hana Bielčíková", insuree.getName());

        List<ContactAddress> addresses = insuree.getAddresses();
        assertEquals(1, addresses.size());
        ContactAddress address = addresses.get(0);
        assertEquals(ContactAddressType.PERMANENT_RESIDENCE, address.getType());
        assertEquals("CZ", address.getCountry());
        assertEquals("STARÉ SMRKOVICE", address.getCity());
        assertEquals("Staré Smrkovice 6", address.getStreet());
        assertEquals("508 01", address.getZipCode());
        assertEquals("8152152602", insuree.getBirthNumber());

        List<Risk> risks = insuree.getRisks();
        assertEquals(2, risks.size());
        for(int i=0; i<risks.size(); ++i) {
            Risk risk = risks.get(i);
            switch (i) {
                case 0:
                    assertEquals("Pojištění smrti následkem úrazu", risk.getProductName());
                    assertEquals("Úmrtí", risk.getRiskGroup());
                    assertEquals(Long.valueOf(15000000), risk.getInsuredSum().getValue());
                    assertEquals(Integer.valueOf(2), risk.getInsuredSum().getPrecision());
                    assertEquals("CZK", risk.getInsuredSum().getCurrency());
                    break;
                case 1:
                    assertEquals("Základní pojištění smrti z jakýchkoliv příčin", risk.getProductName());
                    assertEquals("Úmrtí", risk.getRiskGroup());
                    assertEquals(Long.valueOf(1000000), risk.getInsuredSum().getValue());
                    assertEquals(Integer.valueOf(2), risk.getInsuredSum().getPrecision());
                    assertEquals("CZK", risk.getInsuredSum().getCurrency());
                    break;
            }
        }
    }
}

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
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceEventsListTest extends NetbankingTest {
    private final String X_JUDGE_CASE = "contracts.insurances.withId.events.list";
    private CountDownLatch mCountDownLatch;
    private ContractEventsListResponse mResponse;

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
    public void testEventList() {
        String id = "3961D3F9E922EEE93E2581E896B34566645FE7E3";

        mNetbankingClient.getContractsResource().getInsurancesResource().withId(id).getEventsResource()
                .list(new CallbackWebApi<ContractEventsListResponse>() {
            @Override
            public void success(ContractEventsListResponse eventsListResponse) {
                mResponse = eventsListResponse;
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

        List<ContractEvent> events = mResponse.getEvents();
        assertEquals(1, events.size());
        ContractEvent event = events.get(0);

        assertEquals("13344534534", event.getNumber());
        assertEquals(TimeUtils.getPlainDate("2015-02-28"), event.getCreationDate());
        assertEquals(ContractEventState.CLOSED, event.getState());
        assertEquals("Odesláno pojistné plnění", event.getSubstate());
        assertEquals(TimeUtils.getPlainDate("2015-10-14"), event.getSubstateDate());
        assertEquals("odesláno pojistné plnění", event.getSubstateInfo());
        assertEquals(Long.valueOf(1000000), event.getAmount().getValue());
        assertEquals(Integer.valueOf(2), event.getAmount().getPrecision());
        assertEquals("CZK", event.getAmount().getCurrency());
        assertEquals(TimeUtils.getPlainDate("2015-03-02"), event.getProcessingDate());

        List<Indemnity> indemnities = event.getIndemnities();
        assertEquals(1, indemnities.size());
        Indemnity indemnity = indemnities.get(0);

        assertEquals(TimeUtils.getPlainDate("2015-10-14"), indemnity.getPaymentDate());
        assertEquals("Prevod na ucet", indemnity.getTransferMethod());
        assertEquals("Tonda Palecek", indemnity.getReceiverName());
        assertEquals(Long.valueOf(500000), indemnity.getAmount().getValue());
        assertEquals(Integer.valueOf(2), indemnity.getAmount().getPrecision());
        assertEquals("CZK", indemnity.getAmount().getCurrency());
    }
}

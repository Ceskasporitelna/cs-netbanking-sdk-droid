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
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.netbanking.NetbankingTest;

import static junit.framework.Assert.assertEquals;

/**
 * The type Accounts with id reservations list page 1 test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.04.16.
 */
public class AccountsWithIdReservationsListPage1Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.reservations.list.page1";
    private CountDownLatch mReservationsSignal;
    private ReservationsListResponse mReservationsListResponse;

    @Override
    public void setUp() {
        super.setUp();
        mReservationsSignal = new CountDownLatch(1);
        JudgeUtils.setJudge(mJudgeClient, X_JUDGE_CASE, mXJudgeSessionHeader);
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.XJUDGE_SESSION_HEADER_NAME, mXJudgeSessionHeader);
        mNetbankingClient.setGlobalHeaders(headers);
    }

    /**
     * Test account reservations page 1 list.
     */
    @Test
    public void testAccountReservationsPage1List() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        PaginatedParameters parameters = new PaginatedParameters(new Pagination(1, 1));
        mNetbankingClient.getAccountsResource().withId(accountId).getReservationsResource().list(parameters,
                new CallbackWebApi<ReservationsListResponse>() {
                    @Override
                    public void success(ReservationsListResponse reservationsListResponse) {
                        mReservationsListResponse = reservationsListResponse;
                        mReservationsSignal.countDown();
                    }

                    @Override
                    public void failure(CsSDKError error) {
                        mReservationsSignal.countDown();
                    }
                });

        try {
            mReservationsSignal.await(20, TimeUnit.SECONDS); // wait for callback
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(Long.valueOf(1), mReservationsListResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mReservationsListResponse.getPageCount());
        assertEquals(Long.valueOf(1), mReservationsListResponse.getPageSize());

        List<Reservation> reservations = mReservationsListResponse.getReservations();
        assertEquals(1, reservations.size());

        Reservation reservation = reservations.get(0);
        assertEquals(ReservationStatus.RESERVED, reservation.getStatus());
        assertEquals(TimeUtils.getISO8601Date("2015-09-18T21:54:58Z"), reservation.getCreationDate());
        assertEquals(TimeUtils.getISO8601Date("2015-09-25T21:54:58Z"), reservation.getExpirationDate());
        assertEquals("AAA Taxi", reservation.getMerchantName());
        assertEquals("Platba kartou", reservation.getDescription());

        assertEquals(Long.valueOf(12750), reservation.getAmount().getValue());
        assertEquals(Integer.valueOf(2), reservation.getAmount().getPrecision());
        assertEquals("CZK", reservation.getAmount().getCurrency());
    }
}

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
 * The type Accounts with id reservations list page 0 test.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.04.16.
 */
public class AccountsWithIdReservationsListPage0Test extends NetbankingTest {
    private final String X_JUDGE_CASE = "accounts.withId.reservations.list.page0";
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
     * Test account reservations page 0 list.
     */
    @Test
    public void testAccountReservationsPage0List() {
        String accountId = "076E1DBCCCD38729A99D93AC8D3E8273237C7E36";
        PaginatedParameters parameters = new PaginatedParameters(new Pagination(0, 1));
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

        assertEquals(Long.valueOf(0), mReservationsListResponse.getPageNumber());
        assertEquals(Long.valueOf(2), mReservationsListResponse.getPageCount());
        assertEquals(Long.valueOf(1), mReservationsListResponse.getPageSize());
        assertEquals(Long.valueOf(1), mReservationsListResponse.getNextPage());

        List<Reservation> reservations = mReservationsListResponse.getReservations();
        assertEquals(1, reservations.size());

        Reservation reservation = reservations.get(0);
        assertEquals(ReservationStatus.RESERVED, reservation.getStatus());
        assertEquals(TimeUtils.getISO8601Date("2015-09-18T21:43:53Z"), reservation.getCreationDate());
        assertEquals(TimeUtils.getISO8601Date("2015-09-25T21:43:53Z"), reservation.getExpirationDate());
        assertEquals("Pizzeria Grosseto", reservation.getMerchantName());
        assertEquals("Antala Staška 32, Praha", reservation.getCzMerchantAddress());
        assertEquals("Platba kartou", reservation.getDescription());

        assertEquals(Long.valueOf(45270), reservation.getAmount().getValue());
        assertEquals(Integer.valueOf(2), reservation.getAmount().getPrecision());
        assertEquals("CZK", reservation.getAmount().getCurrency());
    }
}

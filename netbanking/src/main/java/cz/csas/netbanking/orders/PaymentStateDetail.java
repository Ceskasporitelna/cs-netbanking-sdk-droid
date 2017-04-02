package cz.csas.netbanking.orders;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum State detail.
 * Possible stateDetail values used to display relevant status info in FE are defined in following
 * table (local API spec should define mapping to BE technical statuses):
 * stateDetail	Meaning	                                        StateOk
 * OPN	        Open - not signed yet	                        true
 * STO	        Cancelled - storno	                            false
 * NGD	        Insufficient funds - no disposable balance	    false
 * TRM	        Marked as scheduled payment	                    true
 * FUS	        Unknown - finalized unknown	                    false
 * PNR	        Payment Payment from night register	            true
 * FIN	        Forwarded - finalized neutral                   true
 * FIH	        Forwarded - finalized today (current bankday)	true
 * ABG	        Rejected - rejected, ask advisor	            true
 * INB	        Being processed - in process	                true
 * <p/>
 * API Mapping of stateDetail values to state values is in following table (local API spec could
 * define mapping to BE technical status):
 * stateDetail	Meaning
 * OPEN	        Payment state in an OpenState (OPN)
 * SPOOLED	    Payment state in a SpoolState (NGD, TRM, PNR, INB)
 * CANCELLED	Payment state in a CancelState (STO)
 * CLOSED	    Payment state in a ClosedState (FIH, FIN, ABG)
 * DELETED	    Payment state in a DeleteState (order with deleted flag from BE)
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public enum PaymentStateDetail implements HasValue {

    /**
     * Opn state detail.
     */
    OPN("OPN"),

    /**
     * Sto state detail.
     */
    STO("STO"),

    /**
     * Ngd state detail.
     */
    NGD("NGD"),

    /**
     * Trm state detail.
     */
    TRM("TRM"),

    /**
     * Fus state detail.
     */
    FUS("FUS"),

    /**
     * Pnr state detail.
     */
    PNR("PNR"),

    /**
     * Fin state detail.
     */
    FIN("FIN"),

    /**
     * Fih state detail.
     */
    FIH("FIH"),

    /**
     * Abg state detail.
     */
    ABG("ABG"),

    /**
     * Inb state detail.
     */
    INB("INB"),

    /**
     * Other state detail.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new State detail.
     *
     * @param value the value
     */
    PaymentStateDetail(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }
}

package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Card account limits provides information about Card account limits.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class CardAccountLimits {

    @CsExpose
    private Amount limitATM;

    @CsExpose
    private Amount limitPOS;

    /**
     * Get daily ATM limit on credit line. Daily ATM limit for all credit cards issued to mainAccount.
     *
     * @return the limit atm
     */
    public Amount getLimitATM() {
        return limitATM;
    }

    /**
     * Get daily POS limit on credit line. Daily POS limit for all credit cards issued to mainAccount.
     *
     * @return the limit pos
     */
    public Amount getLimitPOS() {
        return limitPOS;
    }
}

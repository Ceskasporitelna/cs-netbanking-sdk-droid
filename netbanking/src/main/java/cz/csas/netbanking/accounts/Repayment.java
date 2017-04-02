package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Repayment provides information about Repayment.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Repayment extends WebApiEntity {

    @CsExpose
    private Date repaymentDate;

    @CsExpose
    private Amount amount;

    @CsExpose
    private Amount paidAmount;

    /**
     * Get date of the repayment.
     *
     * @return the repayment date
     */
    public Date getRepaymentDate() {
        return repaymentDate;
    }

    /**
     * Get repayment amount. What should be paid.
     *
     * @return the repyment amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Get actual paid amount.
     *
     * @return the paid amount
     */
    public Amount getPaidAmount() {
        return paidAmount;
    }
}

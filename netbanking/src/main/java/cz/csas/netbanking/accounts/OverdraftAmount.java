package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Overdraft amount provides infromation about Overdraft used in {@link MainAccount}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class OverdraftAmount extends Amount {

    /**
     * Instantiates a new Overdraft amount.
     *
     * @param value     the value
     * @param precision the precision
     * @param currency  the currency
     * @param dueDate   the due date
     */
    public OverdraftAmount(Long value, Integer precision, String currency, Date dueDate) {
        super(value, precision, currency);
        this.dueDate = dueDate;
    }

    @CsExpose
    private Date dueDate;

    /**
     * Get due date of overdraft. Only for overdrafts where automatic prolongation is not set.
     *
     * @return the due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set due date of overdraft. Only for overdrafts where automatic prolongation is not set.
     *
     * @param dueDate the due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}

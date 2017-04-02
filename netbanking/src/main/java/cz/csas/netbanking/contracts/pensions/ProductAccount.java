package cz.csas.netbanking.contracts.pensions;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Product account.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ProductAccount extends WebApiEntity {

    @CsExpose
    private Amount amount;

    @CsExpose
    private Date date;

    /**
     * Account balance.
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Account balance.
     * @param amount the amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * Date of the account balance validity.
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Date of the account balance validity.
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}

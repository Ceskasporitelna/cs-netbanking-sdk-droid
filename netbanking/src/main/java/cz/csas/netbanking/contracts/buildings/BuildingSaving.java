package cz.csas.netbanking.contracts.buildings;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Building saving.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class BuildingSaving extends WebApiEntity {

    @CsExpose
    private Amount targetAmount;

    @CsExpose
    private Amount agreedMonthlySavings;

    @CsExpose
    private String expiryDate;

    @CsExpose
    private Amount bonusBearingDepositToPay;

    /**
     * Target amount. Will not be set for loans.
     *
     * @return the target amount
     */
    public Amount getTargetAmount() {
        return targetAmount;
    }

    /**
     * Agreed monthly savings amount for building savings. Will not be set for loans.
     *
     * @return the agreed monthly savings
     */
    public Amount getAgreedMonthlySavings() {
        return agreedMonthlySavings;
    }

    /**
     * Notice period expiry date. Not set for loans.
     *
     * @return the expiry date
     */
    public String getExpiryDate() {
        return expiryDate;
    }

    /**
     * Remaining deposit to be paid to Building Savings till the end of this year to get annual maximal bonus.
     *
     * @return the bonus bearing deposit to pay
     */
    public Amount getBonusBearingDepositToPay() {
        return bonusBearingDepositToPay;
    }
}

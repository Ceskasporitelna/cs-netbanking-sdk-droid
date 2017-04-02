package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Saving provides information about Saving. Used in {@link MainAccount}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public class Saving {

    @CsExpose
    private Double interestRateOverLimit;

    @CsExpose
    private Amount interestRateLimit;

    @CsExpose
    @CsSerializedName(value = "cz-bonusInterestRate")
    private Double czBonusInterestRate;

    private SavingGoal savingGoal;

    @CsExpose
    @CsSerializedName("savingGoal")
    private String savingGoalRaw;

    @CsExpose
    private Amount targetAmount;

    @CsExpose
    private Amount minimumBalance;

    @CsExpose
    private Date nextProlongation;

    @CsExpose
    private Amount extraSavingMaximum;

    @CsExpose
    @CsSerializedName(value = "cz-extraSavingMaximumMonthly")
    private Amount czExtraSavingMaximumMonthly;

    /**
     * Get interest rate which applies to value over limit.
     *
     * @return the interest rate over limit
     */
    public Double getInterestRateOverLimit() {
        return interestRateOverLimit;
    }

    /**
     * Get limit amount for basic credit interest rate used for some saving accounts.
     *
     * @return the interest rate limit
     */
    public Amount getInterestRateLimit() {
        return interestRateLimit;
    }

    /**
     * Get bonus interest rates which can be gained if certain conditions are met.
     *
     * @return the bonus interest rate
     */
    public Double getCzBonusInterestRate() {
        return czBonusInterestRate;
    }

    /**
     * Get saving purpose code (for some savings accounts). Possible values are
     * ELECTRONICS, WHITE_GOODS, HOLIDAYS, SPORT_EQUIPMENT, FURNITURE, CARS_AND_ACCESSORIES,
     * HOBBIES_AND_GARDEN, GIFTS_AND_PARTIES, HEALTH, STUDIES, HOUSING, PERSONAL.
     *
     * @return the saving goal
     */
    public SavingGoal getSavingGoal() {
        if (savingGoal == null && savingGoalRaw != null)
            savingGoal = EnumUtils.translateToEnum(SavingGoal.class, savingGoalRaw);
        return savingGoal;
    }

    /**
     * Get saving goal raw.
     *
     * @return the saving goal raw
     */
    public String getSavingGoalRaw() {
        return savingGoalRaw;
    }

    /**
     * Get target amount of the saving.
     *
     * @return the target amount
     */
    public Amount getTargetAmount() {
        return targetAmount;
    }

    /**
     * Get minimum balance for some current and savings accounts.
     *
     * @return the minimum balance
     */
    public Amount getMinimumBalance() {
        return minimumBalance;
    }

    /**
     * Get the next prolongation date, when BE system will automatically credit regular deposit
     * amount on saving account or calculate and transfer interest on term deposit.
     *
     * @return the next prolongation
     */
    public Date getNextProlongation() {
        return nextProlongation;
    }

    /**
     * Get maximum amount of own transfer from current (master) to saving account (slave, this) used
     * only for some saving accounts. Currently used only for Individualní spoření. Field contains
     * maximum amount for current month (maximum for month minus already executed payments in
     * current month).
     *
     * @return the extra saving maximum
     */
    public Amount getExtraSavingMaximum() {
        return extraSavingMaximum;
    }

    /**
     * Get maximum amount of own transfer from current (master) to saving account (slave, this) used
     * only for some saving accounts. Currently used only for Individualni sporeni. Field contains
     * maximum possible amount for all months (regardless already created payments).
     *
     * @return the extra saving maximum monthly
     */
    public Amount getCzExtraSavingMaximumMonthly() {
        return czExtraSavingMaximumMonthly;
    }
}

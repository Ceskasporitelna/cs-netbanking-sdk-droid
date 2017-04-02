package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Loan provides information about Loan.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public class Loan {

    @CsExpose
    private Date interestRateToDate;

    @CsExpose
    private Amount loanAmount;

    @CsExpose
    private Date maturityDate;

    @CsExpose
    private Amount remainingLoanAmount;

    @CsExpose
    private Date drawdownToDate;

    @CsExpose
    private Amount drawdownAmount;

    @CsExpose
    private Amount outstandingDebt;

    @CsExpose
    @CsSerializedName(value = "cz-lumpsumRepayment")
    private Amount czLumpsumRepayment;

    private InstallmentFrequency installmentFrequency;

    @CsExpose
    @CsSerializedName("installmentFrequency")
    private String installmentFrequencyRaw;

    @CsExpose
    private Integer installmentDay;

    @CsExpose
    private Amount nextRateAmount;

    @CsExpose
    private Date nextRateDate;

    /**
     * Get interest rate validity date for mortgages.
     *
     * @return the interest rate to date
     */
    public Date getInterestRateToDate() {
        return interestRateToDate;
    }

    /**
     * Get contracted value of the loan.
     *
     * @return the loan amount
     */
    public Amount getLoanAmount() {
        return loanAmount;
    }

    /**
     * Get maturity date for mortgage.
     *
     * @return the maturity date
     */
    public Date getMaturityDate() {
        return maturityDate;
    }

    /**
     * Get remaining amount for utilization for loan. Basically this is disposable balance for
     * loans.
     *
     * @return the remaining loan amount
     */
    public Amount getRemainingLoanAmount() {
        return remainingLoanAmount;
    }

    /**
     * Get last date when you can use money for loan and mortgages.
     *
     * @return the drawdown to date
     */
    public Date getDrawdownToDate() {
        return drawdownToDate;
    }

    /**
     * Get actual drawdown amount for mortgage.
     *
     * @return the drawdown amount
     */
    public Amount getDrawdownAmount() {
        return drawdownAmount;
    }

    /**
     * Get value of the principal for loans and mortgages. Value which should be paid back to
     * bank.
     *
     * @return the outstanding debt
     */
    public Amount getOutstandingDebt() {
        return outstandingDebt;
    }

    /**
     * Get amount of money to onetime repay whole loan.
     *
     * @return the lumpsum repayment
     */
    public Amount getCzLumpsumRepayment() {
        return czLumpsumRepayment;
    }

    /**
     * Get frequency of the repayment. Possible values are MONTHLY, QUARTERLY, HALFYEARLY, YEARLY,
     * WEEKLY.
     *
     * @return the installment frequency
     */
    public InstallmentFrequency getInstallmentFrequency() {
        if (installmentFrequency == null && installmentFrequencyRaw != null)
            installmentFrequency = EnumUtils.translateToEnum(InstallmentFrequency.class, installmentFrequencyRaw);
        return installmentFrequency;
    }

    /**
     * Get installment frequency raw.
     *
     * @return the installment frequency raw
     */
    public String getInstallmentFrequencyRaw() {
        return installmentFrequencyRaw;
    }

    /**
     * Get day of the month when repayment should be paid (f.e. 22).
     *
     * @return the installment day
     */
    public Integer getInstallmentDay() {
        return installmentDay;
    }

    /**
     * Get loans and mortgages repayment amount.
     *
     * @return the next rate amount
     */
    public Amount getNextRateAmount() {
        return nextRateAmount;
    }

    /**
     * Get next date of the repayment (for loans)..
     *
     * @return the next rate date
     */
    public Date getNextRateDate() {
        return nextRateDate;
    }
}

package cz.csas.netbanking.contracts.buildings;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Loan.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Loan extends WebApiEntity {

    @CsExpose
    private Amount loanAmount;

    @CsExpose
    private Amount loanInstallment;

    @CsExpose
    private Amount additionalSavings;

    @CsExpose
    private Amount paymentInsurance;

    @CsExpose
    private Date interestRateFromDate;

    @CsExpose
    private Date interestRateToDate;

    /**
     * Total contracted building loan amount.
     *
     * @return the loan amount
     */
    public Amount getLoanAmount() {
        return loanAmount;
    }

    /**
     * Installment part of the loan monthly repayment.
     *
     * @return the loan installment
     */
    public Amount getLoanInstallment() {
        return loanInstallment;
    }

    /**
     * Saving part of the loan monthly repayment.
     *
     * @return the additional savings
     */
    public Amount getAdditionalSavings() {
        return additionalSavings;
    }

    /**
     * Insurance part of the loan monthly repayment.
     *
     * @return the payment insurance
     */
    public Amount getPaymentInsurance() {
        return paymentInsurance;
    }

    /**
     * Current interest rate is valid from this date. Filled only for loans.
     *
     * @return the interest from date
     */
    public Date getInterestRateFromDate() {
        return interestRateFromDate;
    }

    /**
     * Current interest rate is valid to this date. Filled only for loans in case of variable
     * interest rate. If interest rate is fixed, this field is empty.
     *
     * @return the interest to date
     */
    public Date getInterestRateToDate() {
        return interestRateToDate;
    }
}

package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Tax benefit.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class TaxBenefit extends WebApiEntity {

    @CsExpose
    private Amount taxDeductiblePremium;

    @CsExpose
    private Amount recommendedDeposit;

    @CsExpose
    private String recommendedDepositText;

    @CsExpose
    private PaymentTemplate paymentTemplate;

    /**
     * Tax deductable premium.
     *
     * @return the tax deductible premium
     */
    public Amount getTaxDeductiblePremium() {
        return taxDeductiblePremium;
    }

    /**
     * Recommended extraordinary deposit for maximum tax deduction.
     *
     * @return the recommended deposit
     */
    public Amount getRecommendedDeposit() {
        return recommendedDeposit;
    }

    /**
     * Recommended extraordinary deposit for maximum tax deduction.
     *
     * @return the recommended deposit text
     */
    public String getRecommendedDepositText() {
        return recommendedDepositText;
    }

    /**
     * Contains data that should be prefilled to domestic payment form.
     *
     * @return the payment template
     */
    public PaymentTemplate getPaymentTemplate() {
        return paymentTemplate;
    }
}

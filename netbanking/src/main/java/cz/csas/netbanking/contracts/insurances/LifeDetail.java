package cz.csas.netbanking.contracts.insurances;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Life detail.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class LifeDetail extends Life {

    @CsExpose
    private String contractTerminationReason;

    @CsExpose
    @CsSerializedName(value = "cz-contractEndReason")
    private String czContractEndReason;

    @CsExpose
    private String premiumPaymentMethodI18N;

    @CsExpose
    private Date premiumLastPaid;

    @CsExpose
    private Double technicalInterestRate;

    @CsExpose
    private EmployerBenefit employerBenefit;

    @CsExpose
    private Immobilization immobilization;

    @CsExpose
    private List<PaymentTemplate> paymentTemplates;

    @CsExpose
    @CsSerializedName(value = "cz-capitalValueMaxWithdrawal")
    private Amount czCapitalValueMaxWithdrawal;

    /**
     * Reason of possible contract termination
     *
     * @return the contract termination reason
     */
    public String getContractTerminationReason() {
        return contractTerminationReason;
    }

    /**
     * In case of CLOSED contract, this means the reason of the termination. This field si localized.
     *
     * @return the cz contract end reason
     */
    public String getCzContractEndReason() {
        return czContractEndReason;
    }

    /**
     * Technique for the premium payment
     *
     * @return the premium payment method i 18 n
     */
    public String getPremiumPaymentMethodI18N() {
        return premiumPaymentMethodI18N;
    }

    /**
     * Date of the last premium payment
     *
     * @return the premium last paid
     */
    public Date getPremiumLastPaid() {
        return premiumLastPaid;
    }

    /**
     * Technical interest rate. Value in percentage, e.g. 0,5 will be displayed as 0,5 %.
     *
     * @return the technical interest rate
     */
    public Double getTechnicalInterestRate() {
        return technicalInterestRate;
    }

    /**
     * Employer benefit info
     *
     * @return the employer benefit
     */
    public EmployerBenefit getEmployerBenefit() {
        return employerBenefit;
    }

    /**
     * Immobilization info
     *
     * @return the immobilization
     */
    public Immobilization getImmobilization() {
        return immobilization;
    }

    /**
     * Gets payment templates.
     *
     * @return the payment templates
     */
    public List<PaymentTemplate> getPaymentTemplates() {
        return paymentTemplates;
    }

    /**
     * Maximum amount that can be withdrawn from capital value
     *
     * @return the cz capital value max withdrawal
     */
    public Amount getCzCapitalValueMaxWithdrawal() {
        return czCapitalValueMaxWithdrawal;
    }
}

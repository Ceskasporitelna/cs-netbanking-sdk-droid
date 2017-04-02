package cz.csas.netbanking.contracts.insurances;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Life.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Life extends WebApiEntity {

    private Interval premiumPaymentInterval;

    @CsExpose
    @CsSerializedName("premiumPaymentInterval")
    private String premiumPaymentIntervalRaw;

    @CsExpose
    private Amount premium;

    @CsExpose
    private Date contractStartDate;

    @CsExpose
    private Date contractEndDate;

    @CsExpose
    private Amount insuredAmount;

    @CsExpose
    private Amount currentCapitalValue;

    @CsExpose
    private Date lastPremiumDate;

    @CsExpose
    private Amount lastPremiumPaid;

    @CsExpose
    private Date contractTerminationDate;

    @CsExpose
    private List<String> flags;

    /**
     * Payment Interval. ENUM: ONCE, MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, UNKNOWN
     *
     * @return the premium payment interval
     */
    public Interval getPremiumPaymentInterval() {
        if (premiumPaymentInterval == null && premiumPaymentIntervalRaw != null)
            premiumPaymentInterval = EnumUtils.translateToEnum(Interval.class, premiumPaymentIntervalRaw);
        return premiumPaymentInterval;
    }

    /**
     * Get premium payment interval raw.
     *
     * @return the premium payment interval raw
     */
    public String getPremiumPaymentIntervalRaw() {
        return premiumPaymentIntervalRaw;
    }

    /**
     * The agreed premium for the specific insurance.
     *
     * @return the premium
     */
    public Amount getPremium() {
        return premium;
    }

    /**
     * The contract start date.
     *
     * @return the contract start date
     */
    public Date getContractStartDate() {
        return contractStartDate;
    }

    /**
     * The agreed end dates of the insurance contract.
     *
     * @return the contract end date
     */
    public Date getContractEndDate() {
        return contractEndDate;
    }

    /**
     * The agreed amount insured or risk covered by the insurance.
     *
     * @return the insured amount
     */
    public Amount getInsuredAmount() {
        return insuredAmount;
    }

    /**
     * Capital value of the insurance. Amount of money in saving part of the insurance.
     *
     * @return the current capital value
     */
    public Amount getCurrentCapitalValue() {
        return currentCapitalValue;
    }

    /**
     * Date of possible contract termination
     *
     * @return the contract termination date
     */
    public Date getContractTerminationDate() {
        return contractTerminationDate;
    }


    /**
     * TODO:
     */
    public Date getLastPremiumDate() {
        return lastPremiumDate;
    }

    /**
     * TODO:
     */
    public Amount getLastPremiumPaid() {
        return lastPremiumPaid;
    }

    /**
     * Array of flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

package cz.csas.netbanking.cards;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Card limit provides information about Card limit.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class CardLimit extends WebApiEntity {

    private CardLimitType limitType;

    @CsExpose
    @CsSerializedName("limitType")
    private String limitTypeRaw;

    private CardLimitPeriod limitPeriod;

    @CsExpose
    @CsSerializedName("limitPeriod")
    private String limitPeriodRaw;

    @CsExpose
    private Amount limit;

    @CsExpose
    private Amount temporaryLimit;

    @CsExpose
    private Date temporaryLimitExpiration;

    @CsExpose
    private Amount bankLimit;


    /**
     * Instantiates a new Card limit.
     *
     * @param limitType                the limit type
     * @param limitPeriod              the limit period
     * @param limit                    the limit
     * @param temporaryLimit           the temporary limit
     * @param temporaryLimitExpiration the temporary limit expiration
     * @param bankLimit                the bank limit
     */
    public CardLimit(CardLimitType limitType, CardLimitPeriod limitPeriod, Amount limit, Amount temporaryLimit, Date temporaryLimitExpiration, Amount bankLimit) {
        this.limitTypeRaw = limitType.getValue();
        this.limitPeriodRaw = limitPeriod.getValue();
        this.limit = limit;
        this.temporaryLimit = temporaryLimit;
        this.temporaryLimitExpiration = temporaryLimitExpiration;
        this.bankLimit = bankLimit;
    }

    public CardLimit(CardLimitType limitType, String limitPeriod, Amount limit, Amount temporaryLimit, Date temporaryLimitExpiration, Amount bankLimit) {
        this.limitTypeRaw = limitType.getValue();
        this.limitPeriodRaw = limitPeriod;
        this.limit = limit;
        this.temporaryLimit = temporaryLimit;
        this.temporaryLimitExpiration = temporaryLimitExpiration;
        this.bankLimit = bankLimit;
    }

    public CardLimit(String limitType, CardLimitPeriod limitPeriod, Amount limit, Amount temporaryLimit, Date temporaryLimitExpiration, Amount bankLimit) {
        this.limitTypeRaw = limitType;
        this.limitPeriodRaw = limitPeriod.getValue();
        this.limit = limit;
        this.temporaryLimit = temporaryLimit;
        this.temporaryLimitExpiration = temporaryLimitExpiration;
        this.bankLimit = bankLimit;
    }

    public CardLimit(String limitType, String limitPeriod, Amount limit, Amount temporaryLimit, Date temporaryLimitExpiration, Amount bankLimit) {
        this.limitTypeRaw = limitType;
        this.limitPeriodRaw = limitPeriod;
        this.limit = limit;
        this.temporaryLimit = temporaryLimit;
        this.temporaryLimitExpiration = temporaryLimitExpiration;
        this.bankLimit = bankLimit;
    }

    /**
     * Get limit type defines ATM, POS, internet/eCommerce, total limits.
     * Possible Values: ATM, POS, INTERNET
     *
     * @return the limit type
     */
    public CardLimitType getLimitType() {
        if (limitType == null && limitTypeRaw != null)
            limitType = EnumUtils.translateToEnum(CardLimitType.class, limitTypeRaw);
        return limitType;
    }

    /**
     * Get limit type raw.
     *
     * @return the limit type raw
     */
    public String getLimitTypeRaw() {
        return limitTypeRaw;
    }

    /**
     * Get bank limit's period in days defined for limit type (default daily - 1D).
     * Possible Values: 1D, 2D, 3D, 5D, 7D, 10D, 15D, 30D
     *
     * @return the limit period
     */
    public CardLimitPeriod getLimitPeriod() {
        if (limitPeriod == null && limitPeriodRaw != null)
            limitPeriod = EnumUtils.translateToEnum(CardLimitPeriod.class, limitPeriodRaw);
        return limitPeriod;
    }

    /**
     * Get limit period raw.
     *
     * @return the limit period raw
     */
    public String getLimitPeriodRaw() {
        return limitPeriodRaw;
    }

    /**
     * Get current limit amount valid for limit's type and period.
     *
     * @return the limit
     */
    public Amount getLimit() {
        return limit;
    }

    /**
     * Get temporary limit amount valid for limit's type and period.
     *
     * @return the temporary limit
     */
    public Amount getTemporaryLimit() {
        return temporaryLimit;
    }

    /**
     * Get temporary limit expiration date for limit's type and period.
     * Field is mandatory if temporatyLimits are changed by PUT call.
     * It is possible to set temporaryLimitExpiration up to 120 hours to the future.
     *
     * @return the temporary limit expiration
     */
    public Date getTemporaryLimitExpiration() {
        return temporaryLimitExpiration;
    }

    /**
     * Get maximum limit amount for card defined by bank valid for limit's type and period.
     *
     * @return the bank limit
     */
    public Amount getBankLimit() {
        return bankLimit;
    }

    /**
     * Set limit type defines ATM, POS, internet/eCommerce, total limits.
     * Possible Values: ATM, POS, INTERNET
     *
     * @param limitType the limit type
     */
    public void setLimitType(CardLimitType limitType) {
        this.limitTypeRaw = limitType.getValue();
    }

    /**
     * Set limit type defines ATM, POS, internet/eCommerce, total limits.
     * Possible Values: ATM, POS, INTERNET
     *
     * @param limitType the limit type
     */
    public void setLimitType(String limitType) {
        this.limitTypeRaw = limitType;
    }

    /**
     * Set bank limit's period in days defined for limit type (default daily - 1D).
     * Possible Values: 1D, 2D, 3D, 5D, 7D, 10D, 15D, 30D
     *
     * @param limitPeriod the limit period
     */
    public void setLimitPeriod(CardLimitPeriod limitPeriod) {
        this.limitPeriodRaw = limitPeriod.getValue();
    }

    /**
     * Set bank limit's period in days defined for limit type (default daily - 1D).
     * Possible Values: 1D, 2D, 3D, 5D, 7D, 10D, 15D, 30D
     *
     * @param limitPeriod the limit period
     */
    public void setLimitPeriod(String limitPeriod) {
        this.limitPeriodRaw = limitPeriod;
    }

    /**
     * Set current limit amount valid for limit's type and period.
     *
     * @param limit the limit
     */
    public void setLimit(Amount limit) {
        this.limit = limit;
    }

    /**
     * Set temporary limit amount valid for limit's type and period.
     *
     * @param temporaryLimit the temporary limit
     */
    public void setTemporaryLimit(Amount temporaryLimit) {
        this.temporaryLimit = temporaryLimit;
    }

    /**
     * Set temporary limit expiration date for limit's type and period.
     * Field is mandatory if temporatyLimits are changed by PUT call.
     * It is possible to set temporaryLimitExpiration up to 120 hours to the future.
     *
     * @param temporaryLimitExpiration the temporary limit expiration
     */
    public void setTemporaryLimitExpiration(Date temporaryLimitExpiration) {
        this.temporaryLimitExpiration = temporaryLimitExpiration;
    }

    /**
     * Set maximum limit amount for card defined by bank valid for limit's type and period.
     *
     * @param bankLimit the bank limit
     */
    public void setBankLimit(Amount bankLimit) {
        this.bankLimit = bankLimit;
    }
}

package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.orders.Symbols;

/**
 * The type Direct debit.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class DirectDebit extends WebApiEntity {

    @CsExpose
    private String number;

    private DirectDebitType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String receiverName;

    @CsExpose
    private AccountNumber receiver;

    @CsExpose
    private String alias;

    @CsExpose
    private Amount limit;

    @CsExpose
    private Amount limitSum;

    @CsExpose
    private Integer limitNumber;

    @CsExpose
    private Date startDate;

    @CsExpose
    private Date endDate;

    @CsExpose
    private Symbols symbols;

    @CsExpose
    private Integer dueMonth;

    @CsExpose
    private Integer dayFrom;

    @CsExpose
    private Integer dayTo;

    @CsExpose
    private Integer versionId;

    @CsExpose
    private Date versionValidityDate;

    @CsExpose
    private Integer periodicity;

    private PeriodCycle periodCycle;

    @CsExpose
    @CsSerializedName("periodCycle")
    private String periodCycleRaw;

    /**
     * Order number of the direct debit approval. It is unique per approval. Several versions of an
     * approval have the same order number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Type of the approval. Possible values are DIRECT_DEBIT or SIPO.
     *
     * @return the type
     */
    public DirectDebitType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(DirectDebitType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Name of the direct debit receiver.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Account number of the receiver.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Approval name chosen by the user.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Limit for the single direct debit.
     *
     * @return the limit
     */
    public Amount getLimit() {
        return limit;
    }

    /**
     * Limit for the whole period.
     *
     * @return the limit sum
     */
    public Amount getLimitSum() {
        return limitSum;
    }

    /**
     * Gets limit number.
     *
     * @return the limit number
     */
    public Integer getLimitNumber() {
        return limitNumber;
    }

    /**
     * Beginning date for the approval.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * End date for the approval.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Symbols structure for Variable symbol, Specific symbol, Constant symbol.
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * The order of the month in the period when direct debits are acceptable. This must he null for
     * period at least one month.
     *
     * @return the due month
     */
    public Integer getDueMonth() {
        return dueMonth;
    }

    /**
     * The number of the day in month. From this day direct debits are acceptable. This can be not
     * null only for period longer then one month.
     *
     * @return the day from
     */
    public Integer getDayFrom() {
        return dayFrom;
    }

    /**
     * The number of the day in month. To this day direct debits are acceptable.
     *
     * @return the day to
     */
    public Integer getDayTo() {
        return dayTo;
    }

    /**
     * Unique identifier for version of the direct debit approval.
     *
     * @return the version id
     */
    public Integer getVersionId() {
        return versionId;
    }

    /**
     * Date when the version comes into use.
     *
     * @return the version validity date
     */
    public Date getVersionValidityDate() {
        return versionValidityDate;
    }

    /**
     * Number of period cycles in one period.
     *
     * @return the periodicity
     */
    public Integer getPeriodicity() {
        return periodicity;
    }

    /**
     * Unit of the period cycle. Possible values are HALFYEARLY, MONTHLY, QUARTERLY, YEARLY, DAILY,
     * WEEKLY, OTHER.
     *
     * @return the period cycle
     */
    public PeriodCycle getPeriodCycle() {
        if (periodCycle == null && periodCycleRaw != null)
            periodCycle = EnumUtils.translateToEnum(PeriodCycle.class, periodCycleRaw);
        return periodCycle;
    }

    /**
     * Get period cycle raw.
     *
     * @return the period cycle raw
     */
    public String getPeriodCycleRaw() {
        return periodCycleRaw;
    }
}

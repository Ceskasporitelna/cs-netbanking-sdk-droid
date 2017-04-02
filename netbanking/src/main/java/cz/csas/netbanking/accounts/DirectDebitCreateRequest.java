package cz.csas.netbanking.accounts;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.orders.Symbols;

/**
 * The type Direct debit create request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class DirectDebitCreateRequest extends WebApiRequest {

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
    private Integer numberLimit;

    @CsExpose
    private String startDate;

    @CsExpose
    private String endDate;

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

    private DirectDebitCreateRequest(Builder builder) {
        typeRaw = builder.type;
        receiverName = builder.receiverName;
        receiver = builder.receiver;
        alias = builder.alias;
        limit = builder.limit;
        limitSum = builder.limitSum;
        numberLimit = builder.numberLimit;
        startDate = TimeUtils.getPlainDateString(builder.startDate);
        endDate = TimeUtils.getPlainDateString(builder.endDate);
        symbols = builder.symbols;
        dueMonth = builder.dueMonth;
        dayFrom = builder.dayFrom;
        dayTo = builder.dayTo;
        versionId = builder.versionId;
        versionValidityDate = builder.versionValidityDate;
        periodicity = builder.periodicity;
        periodCycleRaw = builder.periodCycle;
    }

    /**
     * Get type of the approval. Possible values are DIRECT_DEBIT or SIPO.
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
     * Get name of the direct debit receiver.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Get account number of the receiver.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Get approval name chosen by the user.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Get limit for the single direct debit.
     *
     * @return the limit
     */
    public Amount getLimit() {
        return limit;
    }

    /**
     * Get limit for the whole period.
     *
     * @return the limit sum
     */
    public Amount getLimitSum() {
        return limitSum;
    }

    /**
     * Gets number limit.
     *
     * @return the number limit
     */
    public Integer getNumberLimit() {
        return numberLimit;
    }

    /**
     * Get beginning date for the approval.
     *
     * @return the start date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * Get end date for the approval.
     *
     * @return the end date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Get symbols structure for Variable symbol, Specific symbol, Constant symbol.
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * Ge the order of the month in the period when direct debits are acceptable. This must he null for period at least one month.
     *
     * @return the due month
     */
    public Integer getDueMonth() {
        return dueMonth;
    }

    /**
     * Get the number of the day in month. From this day direct debits are acceptable. This can be not null only for period longer then one month.
     *
     * @return the day from
     */
    public Integer getDayFrom() {
        return dayFrom;
    }

    /**
     * Get the number of the day in month. To this day direct debits are acceptable.
     *
     * @return the day to
     */
    public Integer getDayTo() {
        return dayTo;
    }

    /**
     * Get unique identifier for version of the direct debit approval.
     *
     * @return the version id
     */
    public Integer getVersionId() {
        return versionId;
    }

    /**
     * Get date when the version comes into use.
     *
     * @return the version validity date
     */
    public Date getVersionValidityDate() {
        return versionValidityDate;
    }

    /**
     * Get number of period cycles in one period.
     *
     * @return the periodicity
     */
    public Integer getPeriodicity() {
        return periodicity;
    }

    /**
     * Get unit of the period cycle. Possible values are HALFYEARLY, MONTHLY, QUARTERLY, YEARLY, DAILY,
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

    /**
     * The Builder class for DirectDebitCreateRequest.
     */
    public static final class Builder {
        private String type;
        private String receiverName;
        private AccountNumber receiver;
        private String alias;
        private Amount limit;
        private Amount limitSum;
        private Integer numberLimit;
        private Date startDate;
        private Date endDate;
        private Symbols symbols;
        private Integer dueMonth;
        private Integer dayFrom;
        private Integer dayTo;
        private Integer versionId;
        private Date versionValidityDate;
        private Integer periodicity;
        private String periodCycle;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Type of the approval. Possible values are DIRECT_DEBIT or SIPO.
         *
         * @param directDebitType the direct debit type
         * @return the builder
         */
        public Builder setType(DirectDebitType directDebitType) {
            type = directDebitType.getValue();
            return this;
        }

        /**
         * Type of the approval. Possible values are DIRECT_DEBIT or SIPO.
         *
         * @param directDebitType the direct debit type
         * @return the builder
         */
        public Builder setType(String directDebitType) {
            type = directDebitType;
            return this;
        }

        /**
         * Name of the direct debit receiver.
         *
         * @param receiverName the receiver name
         * @return the builder
         */
        public Builder setReceiverName(String receiverName) {
            this.receiverName = receiverName;
            return this;
        }

        /**
         * Account number of the receiver.
         *
         * @param accountNumber the account number
         * @return the builder
         */
        public Builder setReceiver(AccountNumber accountNumber) {
            receiver = accountNumber;
            return this;
        }

        /**
         * Approval name chosen by the user.
         *
         * @param alias the alias
         * @return the builder
         */
        public Builder setAlias(String alias) {
            this.alias = alias;
            return this;
        }

        /**
         * Limit for the single direct debit.
         *
         * @param amount the amount
         * @return the builder
         */
        public Builder setLimit(Amount amount) {
            limit = amount;
            return this;
        }

        /**
         * Limit for the whole period.
         *
         * @param amount the amount
         * @return the builder
         */
        public Builder setLimitSum(Amount amount) {
            limitSum = amount;
            return this;
        }

        /**
         * Limit for the number of direct debits for the period.
         *
         * @param numberLimit the number limit
         * @return the builder
         */
        public Builder setNumberLimit(Integer numberLimit) {
            this.numberLimit = numberLimit;
            return this;
        }

        /**
         * Beginning date for the approval.
         *
         * @param date the date
         * @return the builder
         */
        public Builder setStartDate(Date date) {
            startDate = date;
            return this;
        }

        /**
         * End date for the approval.
         *
         * @param date the date
         * @return the builder
         */
        public Builder setEndDate(Date date) {
            endDate = date;
            return this;
        }

        /**
         * Symbols structure for Variable symbol, Specific symbol, Constant symbol.
         *
         * @param symbols the symbols
         * @return the builder
         */
        public Builder setSymbols(Symbols symbols) {
            this.symbols = symbols;
            return this;
        }

        /**
         * The order of the month in the period when direct debits are acceptable. This must he null for period at least one month.
         *
         * @param dueMonth the due month
         * @return the builder
         */
        public Builder setDueMonth(Integer dueMonth) {
            this.dueMonth = dueMonth;
            return this;
        }

        /**
         * The number of the day in month. From this day direct debits are acceptable. This can be not null only for period longer then one month.
         *
         * @param dayFrom the day from
         * @return the builder
         */
        public Builder setDayFrom(Integer dayFrom) {
            this.dayFrom = dayFrom;
            return this;
        }

        /**
         * The number of the day in month. To this day direct debits are acceptable.
         *
         * @param dayTo the day to
         * @return the builder
         */
        public Builder setDayTo(Integer dayTo) {
            this.dayTo = dayTo;
            return this;
        }

        /**
         * Unique identifier for version of the direct debit approval.
         *
         * @param versionId the version id
         * @return the builder
         */
        public Builder setVersionId(Integer versionId) {
            this.versionId = versionId;
            return this;
        }

        /**
         * Date when the version comes into use.
         *
         * @param versionValidityDate the version validity date
         * @return the builder
         */
        public Builder setVersionValidityDate(Date versionValidityDate) {
            this.versionValidityDate = versionValidityDate;
            return this;
        }

        /**
         * Number of period cycles in one period.
         *
         * @param periodicity the periodicity
         * @return the builder
         */
        public Builder setPeriodicity(Integer periodicity) {
            this.periodicity = periodicity;
            return this;
        }

        /**
         * Unit of the period cycle. Possible values are HALFYEARLY, MONTHLY, QUARTERLY, YEARLY, DAILY, WEEKLY, OTHER.
         *
         * @param periodCycle the period cycle
         * @return the builder
         */
        public Builder setPeriodCycle(PeriodCycle periodCycle) {
            this.periodCycle = periodCycle.getValue();
            return this;
        }

        /**
         * Unit of the period cycle. Possible values are HALFYEARLY, MONTHLY, QUARTERLY, YEARLY, DAILY, WEEKLY, OTHER.
         *
         * @param periodCycle the period cycle
         * @return the builder
         */
        public Builder setPeriodCycle(String periodCycle) {
            this.periodCycle = periodCycle;
            return this;
        }

        /**
         * Build direct debit create request.
         *
         * @return the direct debit create request
         */
        public DirectDebitCreateRequest build() {
            return new DirectDebitCreateRequest(this);
        }
    }
}

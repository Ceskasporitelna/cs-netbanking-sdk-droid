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
 * The type Standing order create request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class StandingOrderCreateRequest extends WebApiRequest {

    @CsExpose
    private StandingOrderType type;

    private StandingOrderSubtype subtype;

    @CsExpose
    @CsSerializedName("subtype")
    private String subtypeRaw;

    @CsExpose
    private String alias;

    @CsExpose
    private String receiverName;

    @CsExpose
    private AccountNumber receiver;

    @CsExpose
    private String senderReference;

    @CsExpose
    private Amount amount;

    @CsExpose
    private String nextExecutionDate;

    @CsExpose
    private String lastExecutionDate;

    private ExecutionMode executionMode;

    @CsExpose
    @CsSerializedName("executionMode")
    private String executionModeRaw;

    private ExecutionDueMode executionDueMode;

    @CsExpose
    @CsSerializedName("executionDueMode")
    private String executionDueModeRaw;

    private ExecutionInterval executionInterval;

    @CsExpose
    @CsSerializedName("executionInterval")
    private String executionIntervalRaw;

    @CsExpose
    private Double intervalDueDay;

    @CsExpose
    private Double intervalDueMonth;

    @CsExpose
    private Symbols symbols;

    private StandingOrderCreateRequest(Builder builder) {
        type = builder.type;
        subtypeRaw = builder.subtype;
        alias = builder.alias;
        receiverName = builder.receiverName;
        receiver = builder.receiver;
        senderReference = builder.senderReference;
        amount = builder.amount;
        nextExecutionDate = TimeUtils.getPlainDateString(builder.nextExecutionDate);
        lastExecutionDate = TimeUtils.getPlainDateString(builder.lastExecutionDate);
        executionModeRaw = builder.executionMode;
        executionDueModeRaw = builder.executionDueMode;
        executionIntervalRaw = builder.executionInterval;
        intervalDueDay = builder.intervalDueDay;
        intervalDueMonth = builder.intervalDueMonth;
        symbols = builder.symbols;
    }

    /**
     * Either STANDING_ORDER (there is fixed amount specified which is transferred in defined
     * times) or SWEEP_ORDER (there is specified limit, amount over limit/to limit is
     * transferred in defined times).
     *
     * @return the type
     */
    public StandingOrderType getType() {
        return type;
    }

    /**
     * Relevant only for sweep orders. Either SWEEP_OVER_LIMIT (amount over limit is transferred from account) or SWEEP_UNDER_LIMIT (amount to limit is transferred to account).
     *
     * @return the subtype
     */
    public StandingOrderSubtype getSubtype() {
        if (subtype == null && subtypeRaw != null)
            subtype = EnumUtils.translateToEnum(StandingOrderSubtype.class, subtypeRaw);
        return subtype;
    }

    /**
     * Get subtype raw.
     *
     * @return the subtype raw
     */
    public String getSubtypeRaw() {
        return subtypeRaw;
    }

    /**
     * Alias name of standing order entered by user for his better orientation in standing order list.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Name of the standing order receiver.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Receiver account number. In case of SWEEP_UNDER_LIMIT this is actually sender.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Message for payee set during payment order creation. It is used to identify transaction on receiver side.
     *
     * @return the sender reference
     */
    public String getSenderReference() {
        return senderReference;
    }

    /**
     * The amount of the order in case of standing order. The limit amount in case of sweep order.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Date when the next order is set to be executed. This includes weekends and banking holidays.
     *
     * @return the next execution date
     */
    public String getNextExecutionDate() {
        return nextExecutionDate;
    }

    /**
     * Date when the last order will be processed. Only applicable in combination with executionMode .
     *
     * @return the last execution date
     */
    public String getLastExecutionDate() {
        return lastExecutionDate;
    }

    /**
     * The execution mode defines when or how standing/sweep order will be cancelled, processed the last time. Possible values: UNTIL_DATE (standing order is valid until specific date - field lastExecutionDate), UNTIL_CANCELLATION (standing order is valid forever and must be cancelled by client), AFTER_MAX_ITERATION_EXCEEDED (certain count of executions is specified - field maxIterations) or MAX_AMOUNT_EXCEEDED (maximum amount which can be transferred for this order is specified, if next iteration would exceed this amount it is not executed - field maxAmount).
     *
     * @return the execution mode
     */
    public ExecutionMode getExecutionMode() {
        if (executionMode == null && executionModeRaw != null)
            executionMode = EnumUtils.translateToEnum(ExecutionMode.class, executionModeRaw);
        return executionMode;
    }

    /**
     * Get execution mode raw.
     *
     * @return the execution mode raw
     */
    public String getExecutionModeRaw() {
        return executionModeRaw;
    }

    /**
     * Get execution due mode.
     *
     * @return the execution due mode
     */
    public ExecutionDueMode getExecutionDueMode() {
        if (executionDueMode == null && executionDueModeRaw != null)
            executionDueMode = EnumUtils.translateToEnum(ExecutionDueMode.class, executionDueModeRaw);
        return executionDueMode;
    }

    /**
     * The execution due mode defines how the date when order should be executed is specified. Possible values: DUE_DAY_OF_MONTH (specific number of day in the month is defined) or DUE_LAST_DAY_OF_MONTH (order is executed on last day of particular month).
     *
     * @return the execution due mode raw
     */
    public String getExecutionDueModeRaw() {
        return executionDueModeRaw;
    }

    /**
     * Execution interval defines how often order is executed. Possible values: DAILY, WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, IRREGULAR.
     *
     * @return the execution interval
     */
    public ExecutionInterval getExecutionInterval() {
        if (executionInterval == null && executionIntervalRaw != null)
            executionInterval = EnumUtils.translateToEnum(ExecutionInterval.class, executionIntervalRaw);
        return executionInterval;
    }

    /**
     * Get execution interval raw.
     *
     * @return the execution interval raw
     */
    public String getExecutionIntervalRaw() {
        return executionIntervalRaw;
    }

    /**
     * Value represents order number of the day within particular period when the standing order will be reqularly executed. Possible values: 1-7 (for WEEKLY interval), 1-28 for STANDING_ORDER, 1-27 for type SWEEP_ORDER (for MONTHLY, QUARTERLY, HALFYEARLY and YEARLY - for intervals longer then month also intervalDueMonth is applicable). Field is not relevant for other execution intervals.
     *
     * @return the interval due day
     */
    public Double getIntervalDueDay() {
        return intervalDueDay;
    }

    /**
     * Due date month in execution interval of standing order processing. Represents order number of the month in particular period. Possible values: 1-2 for BI_MONTHLY, 1-3 for QUARTERLY, 1-6 for HALFYEARLY, 1-12 for YEARLY. Field is not relevant for other execution intervals.
     *
     * @return the interval due month
     */
    public Double getIntervalDueMonth() {
        return intervalDueMonth;
    }

    /**
     * Payment symbols
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * The Builder class for StandingOrderCreateRequest.
     */
    public static final class Builder {
        private StandingOrderType type;
        private String subtype;
        private String alias;
        private String receiverName;
        private AccountNumber receiver;
        private String senderReference;
        private Amount amount;
        private Date nextExecutionDate;
        private Date lastExecutionDate;
        private String executionMode;
        private String executionDueMode;
        private String executionInterval;
        private Double intervalDueDay;
        private Double intervalDueMonth;
        private Symbols symbols;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
        }

        /**
         * Either STANDING_ORDER (there is fixed amount specified which is transferred in defined
         * times) or SWEEP_ORDER (there is specified limit, amount over limit/to limit is
         * transferred in defined times).
         *
         * @param standingOrderType the standing order type
         * @return the builder
         */
        public Builder setType(StandingOrderType standingOrderType) {
            type = standingOrderType;
            return this;
        }

        /**
         * Relevant only for sweep orders. Either SWEEP_OVER_LIMIT (amount over limit is transferred from account) or SWEEP_UNDER_LIMIT (amount to limit is transferred to account).
         *
         * @param standingOrderSubtype the standing order subtype
         * @return the builder
         */
        public Builder setSubtype(StandingOrderSubtype standingOrderSubtype) {
            subtype = standingOrderSubtype.getValue();
            return this;
        }

        /**
         * Relevant only for sweep orders. Either SWEEP_OVER_LIMIT (amount over limit is transferred from account) or SWEEP_UNDER_LIMIT (amount to limit is transferred to account).
         *
         * @param standingOrderSubtype the standing order subtype
         * @return the builder
         */
        public Builder setSubtype(String standingOrderSubtype) {
            subtype = standingOrderSubtype;
            return this;
        }

        /**
         * Alias name of standing order entered by user for his better orientation in standing order list.
         *
         * @param alias the alias
         * @return the builder
         */
        public Builder setAlias(String alias) {
            this.alias = alias;
            return this;
        }

        /**
         * Name of the standing order receiver.
         *
         * @param receiverName the receiver name
         * @return the builder
         */
        public Builder setReceiverName(String receiverName) {
            this.receiverName = receiverName;
            return this;
        }

        /**
         * Receiver account number. In case of SWEEP_UNDER_LIMIT this is actually sender.
         *
         * @param accountNumber the receiver account number
         * @return the builder
         */
        public Builder setReceiver(AccountNumber accountNumber) {
            receiver = accountNumber;
            return this;
        }

        /**
         * Message for payee set during payment order creation. It is used to identify transaction on receiver side.
         *
         * @param senderReference the sender reference
         * @return the builder
         */
        public Builder setSenderReference(String senderReference) {
            this.senderReference = senderReference;
            return this;
        }

        /**
         * The amount of the order in case of standing order. The limit amount in case of sweep order.
         *
         * @param amount the amount
         * @return the builder
         */
        public Builder setAmount(Amount amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Date when the next order is set to be executed. This includes weekends and banking holidays.
         *
         * @param date the next execution date
         * @return the builder
         */
        public Builder setNextExecutionDate(Date date) {
            nextExecutionDate = date;
            return this;
        }

        /**
         * Date when the last order will be processed. Only applicable in combination with executionMode .
         *
         * @param date the last execution date
         * @return the builder
         */
        public Builder setLastExecutionDate(Date date) {
            lastExecutionDate = date;
            return this;
        }

        /**
         * The execution mode defines when or how standing/sweep order will be cancelled, processed the last time. Possible values: UNTIL_DATE (standing order is valid until specific date - field lastExecutionDate), UNTIL_CANCELLATION (standing order is valid forever and must be cancelled by client), AFTER_MAX_ITERATION_EXCEEDED (certain count of executions is specified - field maxIterations) or MAX_AMOUNT_EXCEEDED (maximum amount which can be transferred for this order is specified, if next iteration would exceed this amount it is not executed - field maxAmount).
         *
         * @param executionMode the execution mode
         * @return the builder
         */
        public Builder setExecutionMode(ExecutionMode executionMode) {
            this.executionMode = executionMode.getValue();
            return this;
        }

        /**
         * The execution mode defines when or how standing/sweep order will be cancelled, processed the last time. Possible values: UNTIL_DATE (standing order is valid until specific date - field lastExecutionDate), UNTIL_CANCELLATION (standing order is valid forever and must be cancelled by client), AFTER_MAX_ITERATION_EXCEEDED (certain count of executions is specified - field maxIterations) or MAX_AMOUNT_EXCEEDED (maximum amount which can be transferred for this order is specified, if next iteration would exceed this amount it is not executed - field maxAmount).
         *
         * @param executionMode the execution mode
         * @return the builder
         */
        public Builder setExecutionMode(String executionMode) {
            this.executionMode = executionMode;
            return this;
        }

        /**
         * The execution due mode defines how the date when order should be executed is specified. Possible values: DUE_DAY_OF_MONTH (specific number of day in the month is defined) or DUE_LAST_DAY_OF_MONTH (order is executed on last day of particular month).
         *
         * @param executionDueMode the execution due mode
         * @return the builder
         */
        public Builder setExecutionDueMode(ExecutionDueMode executionDueMode) {
            this.executionDueMode = executionDueMode.getValue();
            return this;
        }

        /**
         * The execution due mode defines how the date when order should be executed is specified. Possible values: DUE_DAY_OF_MONTH (specific number of day in the month is defined) or DUE_LAST_DAY_OF_MONTH (order is executed on last day of particular month).
         *
         * @param executionDueMode the execution due mode
         * @return the builder
         */
        public Builder setExecutionDueMode(String executionDueMode) {
            this.executionDueMode = executionDueMode;
            return this;
        }

        /**
         * Execution interval defines how often order is executed. Possible values: DAILY, WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, IRREGULAR.
         *
         * @param executionInterval the execution interval
         * @return the builder
         */
        public Builder setExecutionInterval(ExecutionInterval executionInterval) {
            this.executionInterval = executionInterval.getValue();
            return this;
        }

        /**
         * Execution interval defines how often order is executed. Possible values: DAILY, WEEKLY, MONTHLY, BI_MONTHLY, QUARTERLY, HALFYEARLY, YEARLY, IRREGULAR.
         *
         * @param executionInterval the execution interval
         * @return the builder
         */
        public Builder setExecutionInterval(String executionInterval) {
            this.executionInterval = executionInterval;
            return this;
        }

        /**
         * Value represents order number of the day within particular period when the standing order will be reqularly executed. Possible values: 1-7 (for WEEKLY interval), 1-28 for STANDING_ORDER, 1-27 for type SWEEP_ORDER (for MONTHLY, QUARTERLY, HALFYEARLY and YEARLY - for intervals longer then month also intervalDueMonth is applicable). Field is not relevant for other execution intervals.
         *
         * @param intervalDueDay the interval due day
         * @return the builder
         */
        public Builder setIntervalDueDay(Double intervalDueDay) {
            this.intervalDueDay = intervalDueDay;
            return this;
        }

        /**
         * Due date month in execution interval of standing order processing. Represents order number of the month in particular period. Possible values: 1-2 for BI_MONTHLY, 1-3 for QUARTERLY, 1-6 for HALFYEARLY, 1-12 for YEARLY. Field is not relevant for other execution intervals.
         *
         * @param intervalDueMonth the interval due month
         * @return the builder
         */
        public Builder setIntervalDueMonth(Double intervalDueMonth) {
            this.intervalDueMonth = intervalDueMonth;
            return this;
        }

        /**
         * Payment symbols
         *
         * @param symbols the symbols
         * @return the builder
         */
        public Builder setSymbols(Symbols symbols) {
            this.symbols = symbols;
            return this;
        }

        /**
         * Build standing order create request.
         *
         * @return the standing order create request
         */
        public StandingOrderCreateRequest build() {
            return new StandingOrderCreateRequest(this);
        }
    }
}

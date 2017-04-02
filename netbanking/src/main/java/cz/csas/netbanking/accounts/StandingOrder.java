package cz.csas.netbanking.accounts;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;
import cz.csas.netbanking.orders.Symbols;

/**
 * The type Standing order.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class StandingOrder extends WebApiEntity {

    @CsExpose
    private String number;

    private StandingOrderType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    private StandingOrderSubtype subtype;

    @CsExpose
    @CsSerializedName("subtype")
    private String subtypeRaw;

    @CsExpose
    private String status;

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
    private Double maxIterations;

    @CsExpose
    private Amount maxAmount;

    @CsExpose
    private String startDate;

    @CsExpose
    private List<Date> scheduledExecutionDates;

    @CsExpose
    private Date nextExecutionDate;

    @CsExpose
    private Date realExecutionDate;

    @CsExpose
    private Date lastExecutionDate;

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
    private Integer intervalDueDay;

    @CsExpose
    private Integer intervalDueMonth;

    @CsExpose
    @CsSerializedName(value = "break")
    private BreakPeriod breakPeriod;

    @CsExpose
    private Symbols symbols;

    @CsExpose
    private List<StoppageMonth> stoppages;

    @CsExpose
    private List<String> flags;

    /**
     * Standing order respectively sweep order identifier.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public StandingOrderType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(StandingOrderType.class, typeRaw);
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
     * Gets subtype.
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
     * Represents the status of the order. Only possible value so far is OK.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets alias.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Gets receiver name.
     *
     * @return the receiver name
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * Gets receiver.
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }

    /**
     * Gets sender reference.
     *
     * @return the sender reference
     */
    public String getSenderReference() {
        return senderReference;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Maximum number of iterations - processing of the standing order. Only applicable in combination with executionMode.
     *
     * @return the max iterations
     */
    public Double getMaxIterations() {
        return maxIterations;
    }

    /**
     * Maximum amount to be transferred using the standing order. Only applicable in combination with executionMode.
     *
     * @return the max amount
     */
    public Amount getMaxAmount() {
        return maxAmount;
    }

    /**
     * Date and time since the order is valid from.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return TimeUtils.getISO8601Date(startDate);
    }

    /**
     * Array of execution dates (DATEs) when payments will be executed from this standing order since today until today + 30 days.
     *
     * @return the scheduled execution dates
     */
    public List<Date> getScheduledExecutionDates() {
        return scheduledExecutionDates;
    }

    /**
     * Gets next execution date.
     *
     * @return the next execution date
     */
    public Date getNextExecutionDate() {
        return nextExecutionDate;
    }

    /**
     * Date when the next order will be really executed taking into account weekends and holidays.
     *
     * @return the real execution date
     */
    public Date getRealExecutionDate() {
        return realExecutionDate;
    }

    /**
     * Gets last execution date.
     *
     * @return the last execution date
     */
    public Date getLastExecutionDate() {
        return lastExecutionDate;
    }

    /**
     * Get execution mode.
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
     * Get execution due mode raw.
     *
     * @return the execution due mode raw
     */
    public String getExecutionDueModeRaw() {
        return executionDueModeRaw;
    }

    /**
     * Get execution interval.
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
     * Get interval due day.
     *
     * @return the interval due day
     */
    public Integer getIntervalDueDay() {
        return intervalDueDay;
    }

    /**
     * Gets interval due month.
     *
     * @return the interval due month
     */
    public Integer getIntervalDueMonth() {
        return intervalDueMonth;
    }

    /**
     * Gets break period.
     *
     * @return the break period
     */
    public BreakPeriod getBreakPeriod() {
        return breakPeriod;
    }

    /**
     * Gets symbols.
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * List of months where there is no payment (only applicable with interval IRREGULAR).
     * Possible values: JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER,
     * OCTOBER, NOVEMBER, DECEMBER
     *
     * @return the stoppages
     */
    public List<StoppageMonth> getStoppages() {
        return stoppages;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Convience method for getting standing order detail
     *
     * @param callback the callback
     */
    public void get(CallbackWebApi<StandingOrder> callback) {
        if (resource instanceof AccountStandingOrderResource)
            ((AccountStandingOrderResource) resource).get(callback);
    }

    /**
     * Conveinience method for deleting standing order
     *
     * @param callback the callback
     */
    public void delete(CallbackWebApi<StandingOrderResponse> callback) {
        if (resource instanceof AccountStandingOrderResource)
            ((AccountStandingOrderResource) resource).delete(callback);
    }
}

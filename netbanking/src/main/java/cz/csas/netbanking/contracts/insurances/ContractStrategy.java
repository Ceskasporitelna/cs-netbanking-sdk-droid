package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Contract strategy.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractStrategy extends WebApiEntity {

    private ContractStrategyType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    private ContractStrategyGroup group;

    @CsExpose
    @CsSerializedName("group")
    private String groupRaw;

    @CsExpose
    private List<StrategyFund> funds;

    /**
     * Type of the chosen strategy. Possible values: CONSERVATIVE, PROGRESSIVE, BALANCED, CONTROL, ACTUAL_SETTING
     *
     * @return the type
     */
    public ContractStrategyType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ContractStrategyType.class, typeRaw);
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
     * Possible values are STRATEGY, INVESTMENT_MANAGEMENT. That means the funds allocation is fixed given by the chosen strategy, or it is under an investment program, so it is variable depending on current market state.
     *
     * @return the group
     */
    public ContractStrategyGroup getGroup() {
        if (group == null && groupRaw != null)
            group = EnumUtils.translateToEnum(ContractStrategyGroup.class, groupRaw);
        return group;
    }

    /**
     * Get group raw.
     *
     * @return the group raw
     */
    public String getGroupRaw() {
        return groupRaw;
    }

    /**
     * Strategy funds list
     *
     * @return the funds
     */
    public List<StrategyFund> getFunds() {
        return funds;
    }
}

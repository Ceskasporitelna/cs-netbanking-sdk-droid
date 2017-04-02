package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;
import cz.csas.netbanking.Amount;

/**
 * The type Insurance funds list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class FundsListResponse extends ListResponse<Fund> {

    @CsExpose
    private Amount totalInvestedAmount;

    private InvestmentProgram investmentProgram;

    @CsExpose
    @CsSerializedName("investmentProgram")
    private String investmentProgramRaw;

    @CsExpose
    private List<String> flags;

    @CsExpose
    @CsSerializedName(value = "funds", alternate = "items")
    private List<Fund> funds;

    @Override
    protected List<Fund> getItems() {
        return funds;
    }

    /**
     * Get insurance funds.
     *
     * @return the insurance funds
     */
    public List<Fund> getFunds() {
        return funds;
    }

    /**
     * Total invested amount into all funds in CZK.
     *
     * @return the total invested amount
     */
    public Amount getTotalInvestedAmount() {
        return totalInvestedAmount;
    }

    /**
     * Shows, whether a investment program is active for life insurance product. The field can be
     * either blank or filled with 2 values - INVESTMENT_MANAGEMENT or CONSEQ
     *
     * @return the investment program
     */
    public InvestmentProgram getInvestmentProgram() {
        if (investmentProgram == null && investmentProgramRaw != null)
            investmentProgram = EnumUtils.translateToEnum(InvestmentProgram.class, investmentProgramRaw);
        return investmentProgram;
    }

    /**
     * Get investment program raw.
     *
     * @return the investment program raw
     */
    public String getInvestmentProgramRaw() {
        return investmentProgramRaw;
    }

    /**
     * Array of flags for funds.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

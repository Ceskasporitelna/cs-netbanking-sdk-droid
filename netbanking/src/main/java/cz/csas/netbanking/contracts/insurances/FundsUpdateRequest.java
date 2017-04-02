package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Insurance funds update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class FundsUpdateRequest extends WebApiRequest {

    @CsExpose
    private List<FundInfo> funds;

    private InvestmentProgram investmentProgram;

    @CsExpose
    @CsSerializedName("investmentProgram")
    private String investmentProgramRaw;

    /**
     * Instantiates a new Funds update request.
     *
     * @param funds             the funds
     * @param investmentProgram the investment program
     */
    public FundsUpdateRequest(List<FundInfo> funds, String investmentProgram) {
        this.funds = funds;
        this.investmentProgramRaw = investmentProgram;
    }

    /**
     * Instantiates a new Funds update request.
     *
     * @param funds             the funds
     * @param investmentProgram the investment program
     */
    public FundsUpdateRequest(List<FundInfo> funds, InvestmentProgram investmentProgram) {
        this.funds = funds;
        this.investmentProgramRaw = investmentProgram.getValue();
    }

    /**
     * Get funds.
     *
     * @return the funds
     */
    public List<FundInfo> getFunds() {
        return funds;
    }

    /**
     * Set funds.
     *
     * @param funds the funds
     */
    public void setFunds(List<FundInfo> funds) {
        this.funds = funds;
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
     * Shows, whether a investment program is active for life insurance product. The field can be
     * either blank or filled with 2 values - INVESTMENT_MANAGEMENT or CONSEQ
     *
     * @param investmentProgram the investment program
     */
    public void setInvestmentProgram(InvestmentProgram investmentProgram) {
        this.investmentProgramRaw = investmentProgram.getValue();
    }

    /**
     * Shows, whether a investment program is active for life insurance product. The field can be
     * either blank or filled with 2 values - INVESTMENT_MANAGEMENT or CONSEQ
     *
     * @param investmentProgram the investment program
     */
    public void setInvestmentProgram(String investmentProgram) {
        this.investmentProgramRaw = investmentProgram;
    }
}

package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Insurance funds update response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class FundsUpdateResponse extends WebApiEntity implements Signable {

    private SigningObject signingObject;

    @CsExpose
    private List<FundInfo> funds;

    private InvestmentProgram investmentProgram;

    @CsExpose
    @CsSerializedName("investmentProgram")
    private String investmentProgramRaw;

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }

    @Override
    public SigningObject signing() {
        return signingObject;
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
}

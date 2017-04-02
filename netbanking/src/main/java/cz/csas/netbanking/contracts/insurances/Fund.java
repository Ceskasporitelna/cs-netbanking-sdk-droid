package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.netbanking.Amount;

/**
 * The type Fund.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class Fund extends FundInfo {

    @CsExpose
    private String name;

    @CsExpose
    private Amount investedAmount;

    @CsExpose
    private Double investedShare;

    /**
     * Instantiates a new Fund.
     *
     * @param code           the code
     * @param allocation     the allocation
     * @param name           the name
     * @param investedAmount the invested amount
     * @param investedShare  the invested share
     */
    public Fund(String code, Double allocation, String name, Amount investedAmount, Double investedShare) {
        super(code, allocation);
        this.name = name;
        this.investedAmount = investedAmount;
        this.investedShare = investedShare;
    }

    /**
     * Name of fund.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of fund.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Current value invested into fund in CZK
     *
     * @return the invested amount
     */
    public Amount getInvestedAmount() {
        return investedAmount;
    }

    /**
     * Current value invested into fund in CZK
     *
     * @param investedAmount the invested amount
     */
    public void setInvestedAmount(Amount investedAmount) {
        this.investedAmount = investedAmount;
    }

    /**
     * Current value invested into fund in %.
     *
     * @return the invested share
     */
    public Double getInvestedShare() {
        return investedShare;
    }

    /**
     * Current value invested into fund in %.
     *
     * @param investedShare the invested share
     */
    public void setInvestedShare(Double investedShare) {
        this.investedShare = investedShare;
    }

}

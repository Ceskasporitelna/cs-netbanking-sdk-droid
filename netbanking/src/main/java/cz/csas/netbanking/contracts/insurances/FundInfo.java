package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Fund info.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 21 /10/16.
 */
public class FundInfo extends WebApiEntity {

    @CsExpose
    private String code;

    @CsExpose
    private Double allocation;


    /**
     * Instantiates a new Fund info.
     *
     * @param code       the code
     * @param allocation the allocation
     */
    public FundInfo(String code, Double allocation) {
        this.code = code;
        this.allocation = allocation;
    }

    /**
     * Unique code of fund.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Unique code of fund.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * The rate at which the savings component of the premium will be invested in selected funds.
     * Value in percentage, e.g. 63 will be displayed as 63 %.
     *
     * @return the allocation
     */
    public Double getAllocation() {
        return allocation;
    }

    /**
     * The rate at which the savings component of the premium will be invested in selected funds.
     * Value in percentage, e.g. 63 will be displayed as 63 %.
     *
     * @param allocation the allocation
     */
    public void setAllocation(Double allocation) {
        this.allocation = allocation;
    }

}

package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Repayments list response provides list of Repayments.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class RepaymentsListResponse extends ListResponse<Repayment> {

    @CsExpose
    @CsSerializedName(value = "repayments", alternate = "items")
    private List<Repayment> repayments;

    @Override
    protected List<Repayment> getItems() {
        return repayments;
    }

    /**
     * Get list of repayments.
     * Convenience method for {@link #getItems()}
     *
     * @return the repayments
     */
    public List<Repayment> getRepayments() {
        return repayments;
    }
}

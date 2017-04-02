package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;

/**
 * The type Contract payments list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class ContractPaymentsListResponse extends ListResponse<ContractPayment> {

    @CsExpose
    @CsSerializedName(value = "payments", alternate = "items")
    private List<ContractPayment> payments;

    @CsExpose
    private List<String> flags;

    @Override
    protected List<ContractPayment> getItems() {
        return payments;
    }

    /**
     * Get payments.
     *
     * @return the payments
     */
    public List<ContractPayment> getPayments() {
        return payments;
    }

    /**
     * Get flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

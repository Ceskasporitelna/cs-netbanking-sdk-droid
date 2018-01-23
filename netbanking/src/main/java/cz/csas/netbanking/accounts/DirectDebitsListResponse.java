package cz.csas.netbanking.accounts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Direct debits list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class DirectDebitsListResponse extends PaginatedListResponse<DirectDebit, DirectDebitsListResponse> {

    @CsExpose
    @CsSerializedName(value = "directDebits", alternate = "items")
    private List<DirectDebit> directDebits;

    @Override
    protected List<DirectDebit> getItems() {
        return directDebits;
    }

    public List<DirectDebit> getDirectDebits() {
        return directDebits;
    }
}

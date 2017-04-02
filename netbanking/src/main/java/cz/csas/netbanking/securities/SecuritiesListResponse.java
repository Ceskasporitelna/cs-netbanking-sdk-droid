package cz.csas.netbanking.securities;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Securities list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class SecuritiesListResponse extends PaginatedListResponse<Security, SecuritiesListResponse> {

    @CsExpose
    @CsSerializedName(value = "securitiesAccounts", alternate = "items")
    private List<Security> securitiesAccounts;

    @Override
    protected List<Security> getItems() {
        return securitiesAccounts;
    }

    /**
     * Get securities accounts.
     *
     * @return the securities accounts
     */
    public List<Security> getSecuritiesAccounts() {
        return securitiesAccounts;
    }
}

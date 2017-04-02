package cz.csas.netbanking.accounts;

import java.util.Map;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;
import cz.csas.cscore.webapi.SortParameter;
import cz.csas.cscore.webapi.Sortable;

/**
 * The type Accounts parameters. This parameters class provides accounts resource parametrization.
 * See also {@link AccountsResource}
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public class AccountsParameters extends PaginatedParameters implements Sortable<AccountsSortableFields> {

    private static final String PARAM_TYPE = "type";

    private SortParameter<AccountsSortableFields> sortBy;

    private ProductType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    /**
     * Instantiates a new Accounts parameters.
     *
     * @param pagination the pagination
     */
    public AccountsParameters(Pagination pagination) {
        super(pagination);
    }

    /**
     * Instantiates a new Accounts parameters.
     *
     * @param pagination the pagination
     * @param sortBy     the sort by
     * @param type       the type
     */
    public AccountsParameters(Pagination pagination,
                              SortParameter<AccountsSortableFields> sortBy, ProductType type) {
        super(pagination);
        this.sortBy = sortBy;
        this.typeRaw = type.getValue();
    }

    /**
     * Instantiates a new Accounts parameters.
     *
     * @param pagination the pagination
     * @param sortBy     the sort by
     * @param type       the type
     */
    public AccountsParameters(Pagination pagination,
                              SortParameter<AccountsSortableFields> sortBy, String type) {
        super(pagination);
        this.sortBy = sortBy;
        this.typeRaw = type;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> dictionary = super.toDictionary();
        if (typeRaw != null)
            dictionary.put(PARAM_TYPE, typeRaw);

        return dictionary;
    }

    /**
     * Get sort by parameters
     *
     * @return sort parameters
     */
    @Override
    public SortParameter<AccountsSortableFields> getSortBy() {
        return sortBy;
    }

    /**
     * Set sort by parameters.
     *
     * @param sortBy the sort by
     */
    @Override
    public void setSortBy(SortParameter<AccountsSortableFields> sortBy) {
        this.sortBy = sortBy;
    }


    /**
     * Set account type. Possible values are
     * CURRENT (for current accounts),
     * SAVING (for saving accounts),
     * LOAN (for loans)
     *
     * @param type the type
     */
    public void setType(ProductType type) {
        this.typeRaw = type.getValue();
    }

    /**
     * Set account type. Possible values are
     * CURRENT (for current accounts),
     * SAVING (for saving accounts),
     * LOAN (for loans)
     *
     * @param type the type
     */
    public void setType(String type) {
        this.typeRaw = type;
    }

    /**
     * Get Account type. Possible values are
     * CURRENT (for current accounts),
     * SAVING (for saving accounts),
     * LOAN (for loans)
     *
     * @return the type
     */
    public ProductType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ProductType.class, typeRaw);
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
}

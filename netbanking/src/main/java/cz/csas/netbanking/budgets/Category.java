package cz.csas.netbanking.budgets;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Category.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Category {

    private CategoryId id;

    @CsExpose
    @CsSerializedName("id")
    private String idRaw;

    @CsExpose
    private String level;

    /**
     * Instantiates a new Category.
     *
     * @param id    the id
     * @param level the level
     */
    public Category(CategoryId id, String level) {
        this.idRaw = id.getValue();
        this.level = level;
    }

    /**
     * Instantiates a new Category.
     *
     * @param id    the id
     * @param level the level
     */
    public Category(String id, String level) {
        this.idRaw = id;
        this.level = level;
    }

    /**
     * Unique id of watched main category of client transactions. Possible values are
     * NON_REGULAR_INCOME, UNCATEGORIZED_INCOME, TRAVEL_HOLIDAYS, HEALTH, LEISURE, COMMUNICATION,
     * CAR, FOOD, EDUCATION, REGULAR_INCOME, ONLINE_SHOPPING, OTHER_EXPENSES, CLOTHING,
     * UNCATEGORIZED_EXPENSE, FEES, SAVINGS_INVESTMENT, TRANSPORT, ALIMONY_POCKET_MONEY, TAXES,
     * WITHDRAWAL, LIVING_AND_ENERGY.
     *
     * @return the id
     */
    public CategoryId getId() {
        if (id == null && idRaw != null)
            id = EnumUtils.translateToEnum(CategoryId.class, idRaw);
        return id;
    }

    /**
     * Get id raw.
     *
     * @return the id raw
     */
    public String getIdRaw() {
        return idRaw;
    }

    /**
     * Unique id of watched main category of client transactions. Possible values are
     * NON_REGULAR_INCOME, UNCATEGORIZED_INCOME, TRAVEL_HOLIDAYS, HEALTH, LEISURE, COMMUNICATION,
     * CAR, FOOD, EDUCATION, REGULAR_INCOME, ONLINE_SHOPPING, OTHER_EXPENSES, CLOTHING,
     * UNCATEGORIZED_EXPENSE, FEES, SAVINGS_INVESTMENT, TRANSPORT, ALIMONY_POCKET_MONEY, TAXES,
     * WITHDRAWAL, LIVING_AND_ENERGY.     *
     *
     * @param id the id
     */
    public void setId(CategoryId id) {
        this.id = id;
    }

    /**
     * Category level. Currently only "mainCategory" is supported.
     *
     * @return the level
     */
    public String getLevel() {
        return level;
    }

    /**
     * Category level. Currently only "mainCategory" is supported.
     *
     * @param level the level
     */
    public void setLevel(String level) {
        this.level = level;
    }
}

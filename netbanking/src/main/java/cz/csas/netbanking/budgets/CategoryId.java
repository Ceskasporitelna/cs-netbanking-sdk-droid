package cz.csas.netbanking.budgets;

import cz.csas.cscore.webapi.HasValue;

/**
 * Unique id of watched main category of client transactions. Possible values are
 * NON_REGULAR_INCOME, UNCATEGORIZED_INCOME, TRAVEL_HOLIDAYS, HEALTH, LEISURE, COMMUNICATION, CAR,
 * FOOD, EDUCATION, REGULAR_INCOME, ONLINE_SHOPPING, OTHER_EXPENSES, CLOTHING,
 * UNCATEGORIZED_EXPENSE, FEES, SAVINGS_INVESTMENT, TRANSPORT, ALIMONY_POCKET_MONEY, TAXES,
 * WITHDRAWAL, LIVING_AND_ENERGY.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum CategoryId implements HasValue {

    /**
     * Non regular income category id.
     */
    NON_REGULAR_INCOME("NON_REGULAR_INCOME"),

    /**
     * Uncategorized income category id.
     */
    UNCATEGORIZED_INCOME("UNCATEGORIZED_INCOME"),

    /**
     * Travel holidays category id.
     */
    TRAVEL_HOLIDAYS("TRAVEL_HOLIDAYS"),

    /**
     * Health category id.
     */
    HEALTH("HEALTH"),

    /**
     * Leisure category id.
     */
    LEISURE("LEISURE"),

    /**
     * Communication category id.
     */
    COMMUNICATION("COMMUNICATION"),

    /**
     * Car category id.
     */
    CAR("CAR"),

    /**
     * Food category id.
     */
    FOOD("FOOD"),

    /**
     * Education category id.
     */
    EDUCATION("EDUCATION"),

    /**
     * Regular income category id.
     */
    REGULAR_INCOME("REGULAR_INCOME"),

    /**
     * Online shopping category id.
     */
    ONLINE_SHOPPING("ONLINE_SHOPPING"),

    /**
     * Other expenses category id.
     */
    OTHER_EXPENSES("OTHER_EXPENSES"),

    /**
     * Clothing category id.
     */
    CLOTHING("CLOTHING"),

    /**
     * Uncategorized expense category id.
     */
    UNCATEGORIZED_EXPENSE("UNCATEGORIZED_EXPENSE"),

    /**
     * Fees category id.
     */
    FEES("FEES"),

    /**
     * Savings investment category id.
     */
    SAVINGS_INVESTMENT("SAVINGS_INVESTMENT"),

    /**
     * Transport category id.
     */
    TRANSPORT("TRANSPORT"),

    /**
     * Alimony pocket money category id.
     */
    ALIMONY_POCKET_MONEY("ALIMONY_POCKET_MONEY"),

    /**
     * Taxes category id.
     */
    TAXES("TAXES"),

    /**
     * Withdrawal category id.
     */
    WITHDRAWAL("WITHDRAWAL"),

    /**
     * Living and energy category id.
     */
    LIVING_AND_ENERGY("LIVING_AND_ENERGY"),

    /**
     * Other category id.
     */
    OTHER(null);

    private String value;

    CategoryId(String value) {
        this.value = value;
    }

    /**
     * Get value
     *
     * @return
     */
    @Override
    public String getValue() {
        return value;
    }
}

package cz.csas.netbanking.accounts;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Saving goal.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 25 /03/16.
 */
public enum SavingGoal implements HasValue {

    /**
     * Electronics saving goal.
     */
    ELECTRONICS("ELECTRONICS"),

    /**
     * White goods saving goal.
     */
    WHITE_GOODS("WHITE_GOODS"),

    /**
     * Holidays saving goal.
     */
    HOLIDAYS("HOLIDAYS"),

    /**
     * Sport equipment saving goal.
     */
    SPORT_EQUIPMENT("SPORT_EQUIPMENT"),

    /**
     * Furniture saving goal.
     */
    FURNITURE("FURNITURE"),

    /**
     * Cars and accessories saving goal.
     */
    CARS_AND_ACCESSORIES("CARS_AND_ACCESSORIES"),

    /**
     * Hobbies and garden saving goal.
     */
    HOBBIES_AND_GARDEN("HOBBIES_AND_GARDEN"),

    /**
     * Gifts and parties saving goal.
     */
    GIFTS_AND_PARTIES("GIFTS_AND_PARTIES"),

    /**
     * Health saving goal.
     */
    HEALTH("HEALTH"),

    /**
     * Studies saving goal.
     */
    STUDIES("STUDIES"),

    /**
     * Housing saving goal.
     */
    HOUSING("HOUSING"),

    /**
     * Personal saving goal.
     */
    PERSONAL("PERSONAL"),

    /**
     * Other saving goal.
     */
    OTHER("OTHER");

    private String value;

    /**
     * Instantiates a new Saving goal.
     *
     * @param value the value
     */
    SavingGoal(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

}

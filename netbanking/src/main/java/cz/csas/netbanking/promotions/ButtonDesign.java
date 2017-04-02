package cz.csas.netbanking.promotions;

import cz.csas.cscore.webapi.HasValue;

/**
 * Key, describing the look of the main button. Must be one of the following values: DEFAULT BORDER
 * PRIMARY SUCCESS INFO WARNING DANGER LINK, GREY
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public enum ButtonDesign implements HasValue {

    /**
     * Default button design.
     */
    DEFAULT("DEFAULT"),

    /**
     * Border button design.
     */
    BORDER("BORDER"),

    /**
     * Primary button design.
     */
    PRIMARY("PRIMARY"),

    /**
     * Success button design.
     */
    SUCCESS("SUCCESS"),

    /**
     * Info button design.
     */
    INFO("INFO"),

    /**
     * Warning button design.
     */
    WARNING("WARNING "),

    /**
     * Danger button design.
     */
    DANGER("DANGER"),

    /**
     * Link button design.
     */
    LINK("LINK"),

    /**
     * Grey button design.
     */
    GREY("GREY"),

    /**
     * Other button design.
     */
    OTHER(null);

    private String value;

    ButtonDesign(String value) {
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

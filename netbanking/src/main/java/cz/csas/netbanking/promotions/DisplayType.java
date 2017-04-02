package cz.csas.netbanking.promotions;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The Promotion display type
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class DisplayType {

    private DisplayTypeKind displayType;

    @CsExpose
    @CsSerializedName("displayType")
    private String displayTypeRaw;

    private CardDesign cardDesign;

    @CsExpose
    @CsSerializedName("cardDesign")
    private String cardDesignRaw;

    @CsExpose
    private String titleText;

    @CsExpose
    private String sublineText;

    @CsExpose
    private String btnText;

    private ButtonDesign btnDesign;

    @CsExpose
    @CsSerializedName("btnDesign")
    private String btnDesignRaw;

    @CsExpose
    private Integer position;

    @CsExpose
    private Integer column;

    @CsExpose
    private String backgroundImage;

    @CsExpose
    private String mainImage;

    /**
     * The type of the layout for the campaign. Currently only these values are possible:
     * OVERVIEW_CARD
     *
     * @return the display type
     */
    public DisplayTypeKind getDisplayType() {
        if (displayType == null && displayTypeRaw != null)
            displayType = EnumUtils.translateToEnum(DisplayTypeKind.class, displayTypeRaw);
        return displayType;
    }

    /**
     * Get display type raw.
     *
     * @return the display type raw
     */
    public String getDisplayTypeRaw() {
        return displayTypeRaw;
    }

    /**
     * Type of the campaign, possible values are PRODUCT_PROMOTION, PLUGIN_PROMOTION, INFOCARD,
     * SHADOWCARD
     *
     * @return the card design
     */
    public CardDesign getCardDesign() {
        if (cardDesign == null && cardDesignRaw != null)
            cardDesign = EnumUtils.translateToEnum(CardDesign.class, cardDesignRaw);
        return cardDesign;
    }

    /**
     * Get card design raw.
     *
     * @return the card design raw
     */
    public String getCardDesignRaw() {
        return cardDesignRaw;
    }

    /**
     * Title of the promotion.
     *
     * @return the title text
     */
    public String getTitleText() {
        return titleText;
    }

    /**
     * Additional - subline text for the title.
     *
     * @return the subline text
     */
    public String getSublineText() {
        return sublineText;
    }

    /**
     * Labeling of the main button. Can also be empty, if empty we don’t show a button. Max
     * characters: 30 preliminary value can perhaps change later.
     *
     * @return the btn text
     */
    public String getBtnText() {
        return btnText;
    }

    /**
     * Key, describing the look of the main button. Must be one of the following values: DEFAULT
     * BORDER PRIMARY SUCCESS INFO WARNING DANGER LINK, GREY
     *
     * @return the btn design
     */
    public ButtonDesign getBtnDesign() {
        if (btnDesign == null && btnDesignRaw != null)
            btnDesign = EnumUtils.translateToEnum(ButtonDesign.class, btnDesignRaw);
        return btnDesign;
    }

    /**
     * Get btn design raw.
     *
     * @return the btn design raw
     */
    public String getBtnDesignRaw() {
        return btnDesignRaw;
    }

    /**
     * Number of the row in the Overview screen, where the promotion should by displayed. Relevant
     * only for displayType OVERVIEW_CARD
     *
     * @return the position
     */
    public Integer getPosition() {
        return position;
    }

    /**
     * Number of the column in the Overview screen, where the promotion should by displayed.
     * Relevant only for displayType OVERVIEW_CARD
     *
     * @return the column
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * Relative path of url for the background picture published in WCM.
     *
     * @return the background image
     */
    public String getBackgroundImage() {
        return backgroundImage;
    }

    /**
     * Relative path of url for the main picture published in WCM.
     *
     * @return the main image
     */
    public String getMainImage() {
        return mainImage;
    }
}

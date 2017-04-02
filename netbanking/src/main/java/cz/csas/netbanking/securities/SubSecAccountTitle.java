package cz.csas.netbanking.securities;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Amount;

/**
 * The type Sub sec account title.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class SubSecAccountTitle {

    @CsExpose
    private String title;

    @CsExpose
    private String isin;

    @CsExpose
    private Double numberOfShares;

    @CsExpose
    private Amount lastPrice;

    @CsExpose
    private String lastPriceDate;

    @CsExpose
    private Amount marketValue;

    private SecurityType securityType;

    @CsExpose
    @CsSerializedName("securityType")
    private String securityTypeRaw;

    private ProductGroup productGroup;

    @CsExpose
    @CsSerializedName("productGroup")
    private String productGroupRaw;

    @CsExpose
    private String securityIndication;

    /**
     * Name of the security title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * ISIN - identifier of the security title.
     *
     * @return the isin
     */
    public String getIsin() {
        return isin;
    }

    /**
     * Number of securities/shares
     *
     * @return the number of shares
     */
    public Double getNumberOfShares() {
        return numberOfShares;
    }

    /**
     * Last Price of Securities title
     *
     * @return the last price
     */
    public Amount getLastPrice() {
        return lastPrice;
    }

    /**
     * Date of securities last price evaluation
     *
     * @return the last price date
     */
    public Date getLastPriceDate() {
        return TimeUtils.getISO8601Date(lastPriceDate);
    }

    /**
     * Market value of the securities title.
     *
     * @return the market value
     */
    public Amount getMarketValue() {
        return marketValue;
    }

    /**
     * Security Product Type. Possible values: BOND, SHARE, FUND, IPO, OPTION, OTHER, INDEX,
     * CERTIFICATE, INVESTMENT,KNOCKOUT, UNKNOWN.
     *
     * @return the security type
     */
    public SecurityType getSecurityType() {
        if (securityType == null && securityTypeRaw != null)
            securityType = EnumUtils.translateToEnum(SecurityType.class, securityTypeRaw);
        return securityType;
    }

    /**
     * Get security type raw.
     *
     * @return the security type raw
     */
    public String getSecurityTypeRaw() {
        return securityTypeRaw;
    }

    /**
     * Security Product Group. Possible values: BONDS_AND_MORE, GUARANTEE_OF_PRINCIPAL,
     * NO_GUARANTEE_OF_PRINCIPAL, REAL_ESTATE, SHARES, STOCK_AND_MIXED, INVESTMENT, KNOCK_OUT,
     * UNKNOWN.
     *
     * @return the product group
     */
    public ProductGroup getProductGroup() {
        if (productGroup == null && productGroupRaw != null)
            productGroup = EnumUtils.translateToEnum(ProductGroup.class, productGroupRaw);
        return productGroup;
    }

    /**
     * Get product group raw.
     *
     * @return the product group raw
     */
    public String getProductGroupRaw() {
        return productGroupRaw;
    }

    /**
     * Localized security indication depending on security type and product group
     *
     * @return the security indication
     */
    public String getSecurityIndication() {
        return securityIndication;
    }
}

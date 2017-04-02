package cz.csas.netbanking.bundles;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.signing.SignInfo;

/**
 * The type Bundle item.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BundleItem {

    @CsExpose
    private String id;

    @CsExpose
    private SignInfo signInfo;

    /**
     * Instantiates a new Bundle item.
     *
     * @param id       the id
     * @param signInfo the signing object
     */
    public BundleItem(String id, SignInfo signInfo) {
        this.id = id;
        this.signInfo = signInfo;
    }

    /**
     * Payment order identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Payment order identifier.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets bundle sign info.
     *
     * @return the bundle sign info
     */
    public SignInfo getSignInfo() {
        return signInfo;
    }

    /**
     * Sets bundle sign info
     *
     * @param signInfo the bundle sign info
     */
    public void setSignInfo(SignInfo signInfo) {
        this.signInfo = signInfo;
    }
}

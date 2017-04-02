package cz.csas.netbanking.bundles;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Bundle.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Bundle extends WebApiEntity implements Signable {

    @CsExpose
    private String id;

    @CsExpose
    private String name;

    @CsExpose
    private List<BundleItem> items;

    private SigningObject signingObject;

    /**
     * Bundle identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Name of the bundle.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Array of items in bundle. Every item represents payment order for batch sign.
     *
     * @return the items
     */
    public List<BundleItem> getItems() {
        return items;
    }

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

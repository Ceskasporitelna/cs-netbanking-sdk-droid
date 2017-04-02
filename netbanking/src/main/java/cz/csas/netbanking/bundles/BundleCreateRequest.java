package cz.csas.netbanking.bundles;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Bundle create request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class BundleCreateRequest extends WebApiRequest {

    @CsExpose
    private String name;

    @CsExpose
    private List<BundleItem> items;

    public BundleCreateRequest(String name, List<BundleItem> items) {
        this.name = name;
        this.items = items;
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
     * Name of the bundle.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Array of items in bundle. Every item represents payment order for batch sign.
     *
     * @return the items
     */
    public List<BundleItem> getItems() {
        return items;
    }

    /**
     * Array of items in bundle. Every item represents payment order for batch sign.
     *
     * @param items the items
     */
    public void setItems(List<BundleItem> items) {
        this.items = items;
    }
}

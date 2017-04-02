package cz.csas.netbanking.plugins;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Plugins list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class PluginsListResponse extends PaginatedListResponse<Plugin, PluginsListResponse>{

    @CsExpose
    @CsSerializedName(value = "plugins", alternate = "items")
    private List<Plugin> plugins;

    @Override
    protected List<Plugin> getItems() {
        return plugins;
    }

    /**
     * Get plugins.
     *
     * @return the plugins
     */
    public List<Plugin> getPlugins() {
        return plugins;
    }
}

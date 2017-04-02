package cz.csas.netbanking.templates;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Templates list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class TemplatesListResponse extends PaginatedListResponse<Template, PaginatedListResponse> {

    @CsExpose
    @CsSerializedName(value = "templates", alternate = "items")
    private List<Template> templates;

    @Override
    protected List<Template> getItems() {
        return templates;
    }

    /**
     * Get templates.
     *
     * @return the templates
     */
    public List<Template> getTemplates() {
        return templates;
    }
}

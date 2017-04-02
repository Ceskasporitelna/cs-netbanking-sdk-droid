package cz.csas.netbanking.securities;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Sub sec account.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class SubSecAccount {

    @CsExpose
    private String id;

    @CsExpose
    private List<SubSecAccountTitle> titles;

    @CsExpose
    private List<String> flags;

    /**
     * Sub Securities Account ID
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Array of the titles within sub account.
     *
     * @return the titles
     */
    public List<SubSecAccountTitle> getTitles() {
        return titles;
    }

    /**
     * Gets flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

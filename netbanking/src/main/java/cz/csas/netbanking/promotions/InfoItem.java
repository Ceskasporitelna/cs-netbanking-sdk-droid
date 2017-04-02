package cz.csas.netbanking.promotions;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Info item.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 02.09.16.
 */
public class InfoItem {

    @CsExpose
    private String infoName;

    @CsExpose
    private String infoValue;

    /**
     * Get info name.
     *
     * @return the info name
     */
    public String getInfoName() {
        return infoName;
    }

    /**
     * Get info value.
     *
     * @return the info value
     */
    public String getInfoValue() {
        return infoValue;
    }
}

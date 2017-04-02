package cz.csas.netbanking.orders;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Info.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Info {

    @CsExpose
    private List<String> text4x35;

    /**
     * Get message for payee set during payment order creation. It is used to identify transaction on
     * receiver side. Array of texts 4x35
     *
     * @return the text 4 x 35
     */
    public List<String> getText4x35() {
        return text4x35;
    }
}

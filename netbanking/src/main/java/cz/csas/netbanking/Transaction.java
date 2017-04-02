package cz.csas.netbanking;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Transaction provides informations about transaction.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Transaction extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String note;

    @CsExpose
    private List<String> flags;

    /**
     * Get transaction identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Get personal, user specific note for transaction. Max. 4 000 characters.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Get list of flags.
     * Possible flags:
     * Flag	    Description
     *
     * hasStar	Indication that transaction is marked as important for client.
     * hasNote	Not editable flag. But if note is saved/deleted then this flag would
     *          appear/disappear.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

package cz.csas.netbanking;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Transaction update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class TransactionUpdateRequest extends WebApiRequest {

    @CsExpose
    private String id;

    @CsExpose
    private String note;

    @CsExpose
    private List<String> flags;

    public TransactionUpdateRequest(String id, String note, List<String> flags) {
        this.id = id;
        this.note = note;
        this.flags = flags;
    }

    public TransactionUpdateRequest(String note, List<String> flags) {
        this.note = note;
        this.flags = flags;
    }

    /**
     * The transaction id
     *
     * @param id the transaction id
     */
     void setId(String id) {
        this.id = id;
    }

    /**
     * Personal, user specific note for transaction. Max. 4 000 characters.
     *
     * @return the note
     */
    public String getNote() {
        return note;
    }

    /**
     * Personal, user specific note for transaction. Max. 4 000 characters.
     *
     * @param note the note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * List of flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * List of flags.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}

package cz.csas.netbanking;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Statements list response provides list of statements.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 28 /03/16.
 */
public class StatementsListResponse extends PaginatedListResponse<Statement, StatementsListResponse> {

    @CsExpose
    @CsSerializedName(value = "statements", alternate = "items")
    private List<Statement> statements;

    @Override
    protected List<Statement> getItems() {
        return statements;
    }

    /**
     * Get list of statements.
     * Convenience method for {@link #getItems()}
     *
     * @return the statements
     */
    public List<Statement> getStatements() {
        return statements;
    }
}

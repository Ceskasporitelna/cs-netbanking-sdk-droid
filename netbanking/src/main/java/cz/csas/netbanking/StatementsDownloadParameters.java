package cz.csas.netbanking;

import java.util.Map;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.webapi.Parameters;

/**
 * The type Statements download parameters defines format and statement id to search.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.04.16.
 */
public class StatementsDownloadParameters extends Parameters {

    private final String PARAM_FORMAT = "format";
    private final String PARAM_STATEMENT_ID = "statementId";
    private Format format;
    private String formatRaw;
    private String statementId;

    /**
     * Instantiates a new Download statements parameters.
     *
     * @param format      the format
     * @param statementId the statement id
     */
    public StatementsDownloadParameters(Format format, String statementId) {
        this.formatRaw = format.getValue();
        this.statementId = statementId;
    }

    /**
     * Instantiates a new Statements download parameters.
     *
     * @param format      the format
     * @param statementId the statement id
     */
    public StatementsDownloadParameters(String format, String statementId) {
        this.formatRaw = format;
        this.statementId = statementId;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> dictionary = super.toDictionary();

        if (formatRaw != null)
            dictionary.put(PARAM_FORMAT, formatRaw);
        if (getStatementId() != null)
            dictionary.put(PARAM_STATEMENT_ID, getStatementId());

        return dictionary;
    }

    /**
     * Get file format.
     *
     * @return the format
     */
    public Format getFormat() {
        if (format == null && formatRaw != null)
            format = EnumUtils.translateToEnum(Format.class, formatRaw);
        return format;
    }

    /**
     * Get format raw.
     *
     * @return the format raw
     */
    public String getFormatRaw() {
        return formatRaw;
    }

    /**
     * Set file format.
     *
     * @param format the format
     */
    public void setFormat(Format format) {
        this.formatRaw = format.getValue();
    }

    /**
     * Set file format.
     *
     * @param format the format
     */
    public void setFormat(String format) {
        this.formatRaw = format;
    }

    /**
     * Get statement identifier.
     *
     * @return the statement id
     */
    public String getStatementId() {
        return statementId;
    }

    /**
     * Set statement identifier.
     *
     * @param statementId the statement id
     */
    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }
}

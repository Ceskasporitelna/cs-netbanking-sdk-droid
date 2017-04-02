package cz.csas.netbanking;

import java.util.Date;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Statement provides information about statement.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Statement extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private Integer number;

    @CsExpose
    private String statementDate;

    @CsExpose
    private Periodicity periodicity;

    private Format format;

    @CsExpose
    @CsSerializedName("format")
    private String formatRaw;

    private Language language;

    @CsExpose
    @CsSerializedName("language")
    private String languageRaw;

    @CsExpose
    @CsSerializedName(value = "cz-fileTotalNumber")
    private Integer czFileTotalNumber;

    @CsExpose
    @CsSerializedName(value = "cz-fileOrderNumber")
    private Integer czFileOrderNumber;

    /**
     * Get identifier of statement in BE system.
     *
     * @return the identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Get statement sequence number.
     *
     * @return the number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Get timestamp of statement creation.
     *
     * @return the statement date
     */
    public Date getStatementDate() {
        return TimeUtils.getISO8601Date(statementDate);
    }

    /**
     * Get periodicity of account statement creation.
     * Possible values are: DAILY, WEEKLY, BI_WEEKLY, MONTHLY, QUARTERLY, HALFYEARLY, YEARLY,
     * 10_YAERLY, OTHER
     *
     * @return the periodicity
     */
    public Periodicity getPeriodicity() {
        return periodicity;
    }

    /**
     * Get statement format. Possible value is PDF_A4
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
     * Get language version of created statement. ISO 639-1
     * ENUM values: en, de, cs, sk, hr, sr, ro, hu, fr (fr is local specific)
     *
     * @return the language
     */
    public Language getLanguage() {
        if (language == null && languageRaw != null)
            language = EnumUtils.translateToEnum(Language.class, languageRaw);
        return language;
    }

    /**
     * Get language raw.
     *
     * @return the language raw
     */
    public String getLanguageRaw() {
        return languageRaw;
    }

    /**
     * Get number of files for of the whole statement.
     *
     * @return the file total number
     */
    public Integer getCzFileTotalNumber() {
        return czFileTotalNumber;
    }

    /**
     * Get file number - to recognize order of the file if the statement is separated into several files.
     *
     * @return the file order number
     */
    public Integer getCzFileOrderNumber() {
        return czFileOrderNumber;
    }
}

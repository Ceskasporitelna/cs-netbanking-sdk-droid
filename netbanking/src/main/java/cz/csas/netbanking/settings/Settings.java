package cz.csas.netbanking.settings;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Language;

/**
 * The type Settings.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class Settings extends WebApiEntity {

    private Language language;

    @CsExpose
    @CsSerializedName("language")
    private String languageRaw;

    @CsExpose
    private List<String> flags;

    /**
     * Get preferred language. Possible values are cs and en.
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
     * List of flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }
}

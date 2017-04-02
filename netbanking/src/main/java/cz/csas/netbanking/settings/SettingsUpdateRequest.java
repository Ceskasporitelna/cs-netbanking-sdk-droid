package cz.csas.netbanking.settings;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.Language;

/**
 * The type Settings update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class SettingsUpdateRequest extends WebApiRequest {

    private Language language;

    @CsExpose
    @CsSerializedName("language")
    private String languageRaw;

    @CsExpose
    private List<String> flags;

    /**
     * Instantiates a new Settings update request.
     *
     * @param language the language
     * @param flags    the flags
     */
    public SettingsUpdateRequest(String language, List<String> flags) {
        this.languageRaw = language;
        this.flags = flags;
    }

    /**
     * Instantiates a new Settings update request.
     *
     * @param flags    the flags
     * @param language the language
     */
    public SettingsUpdateRequest(Language language, List<String> flags) {
        this.flags = flags;
        this.languageRaw = language.getValue();
    }

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
     * Set preferred language. Possible values are cs and en.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {
        this.languageRaw = language.getValue();
    }

    /**
     * Set preferred language. Possible values are cs and en.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.languageRaw = language;
    }

    /**
     * Get flags.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Set flags.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}

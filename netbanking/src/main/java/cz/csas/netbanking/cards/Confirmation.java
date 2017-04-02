package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Language;

/**
 * The type Confirmation provides information about confirmation.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Confirmation {

    @CsExpose
    private String email;

    private Language language;

    @CsExpose
    @CsSerializedName("language")
    private String languageRaw;

    /**
     * Instantiates a new Confirmation.
     *
     * @param email    the email
     * @param language the language
     */
    public Confirmation(String email, String language) {
        this.email = email;
        this.languageRaw = language;
    }

    /**
     * Instantiates a new Confirmation.
     *
     * @param email    the email
     * @param language the language
     */
    public Confirmation(String email, Language language) {
        this.email = email;
        this.languageRaw = language.getValue();
    }

    /**
     * Get email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get confirmation language. ISO 639-1
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
     * Set language.
     *
     * @param language the language
     */
    public void setLanguage(Language language) {
        this.languageRaw = language.getValue();
    }

    /**
     * Set language.
     *
     * @param language the language
     */
    public void setLanguage(String language) {
        this.languageRaw = language;
    }
}

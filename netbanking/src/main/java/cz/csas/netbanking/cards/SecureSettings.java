package cz.csas.netbanking.cards;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Language;

/**
 * The type Secure settings provides information about secure settings.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class SecureSettings extends WebApiEntity {

    private SecureSettingsStatus status;

    @CsExpose
    @CsSerializedName("status")
    private String statusRaw;

    @CsExpose
    private String pam;

    @CsExpose
    private String phoneNumber;

    private Language language;

    @CsExpose
    @CsSerializedName("language")
    private String languageRaw;

    /**
     * Get 3D secure functionality status.
     * Possible Values: OK, NOT_ACTIVATED
     *
     * @return the status
     */
    public SecureSettingsStatus getStatus() {
        if (status == null && statusRaw != null)
            status = EnumUtils.translateToEnum(SecureSettingsStatus.class, statusRaw);
        return status;
    }

    /**
     * Get status raw.
     *
     * @return the status raw
     */
    public String getStatusRaw() {
        return statusRaw;
    }

    /**
     * Get personal Assurance Message (PAM) that user chose when activate 3D secure.
     *
     * @return the pam
     */
    public String getPam() {
        return pam;
    }

    /**
     * Get phone (used for OTP authentification) number Id coming from Contacts.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Get 3D secure language. ISO 639-1
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
}

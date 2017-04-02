package cz.csas.netbanking.contacts;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Email.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 09.09.16.
 */
public class Email {

    private EmailType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String typeI18N;

    @CsExpose
    private String email;

    /**
     * Email type. ENUM values: PRIVATE, COMPANY, UNKNOWN
     *
     * @return the type
     */
    public EmailType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(EmailType.class, typeRaw);
        return type;
    }

    /**
     * Get type raw.
     *
     * @return the type raw
     */
    public String getTypeRaw() {
        return typeRaw;
    }

    /**
     * Localized name of email type.
     *
     * @return the type i 18 n
     */
    public String getTypeI18N() {
        return typeI18N;
    }

    /**
     * Email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }
}

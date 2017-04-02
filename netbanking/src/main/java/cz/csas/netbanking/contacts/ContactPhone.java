package cz.csas.netbanking.contacts;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;

/**
 * The type Contact phone.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class ContactPhone {

    private ContactPhoneType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String typeI18N;

    @CsExpose
    private String countryCallingCode;

    @CsExpose
    private String phoneNumber;

    /**
     * Phone type. PRIVATE, COMPANY, UNKNOWN
     *
     * @return the type
     */
    public ContactPhoneType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ContactPhoneType.class, typeRaw);
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
     * Localized name of phone type.
     *
     * @return the type i 18 n
     */
    public String getTypeI18N() {
        return typeI18N;
    }

    /**
     * Country calling code as international phone number prefix. E.g.: "0043" or "+43", "00420"
     * or "+420", "00421" or "+421"
     *
     * @return the country calling code
     */
    public String getCountryCallingCode() {
        return countryCallingCode;
    }

    /**
     * Phone number
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

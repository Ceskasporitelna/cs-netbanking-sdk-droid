package cz.csas.netbanking.contacts;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.netbanking.Address;

/**
 * The type Contact address.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class ContactAddress extends Address {

    private ContactAddressType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String typeI18N;

    /**
     * Address type. PERMANENT_RESIDENCE, SECONDARY_RESIDENCE, COMPANY_RESIDENCE, UNKNOWN
     *
     * @return the type
     */
    public ContactAddressType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ContactAddressType.class, typeRaw);
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
     * Localized name of address type.
     *
     * @return the type i18n
     */
    public String getTypeI18N() {
        return typeI18N;
    }
}

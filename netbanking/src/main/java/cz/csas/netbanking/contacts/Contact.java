package cz.csas.netbanking.contacts;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Contact.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class Contact extends WebApiEntity {

    @CsExpose
    private String id;

    private ContactType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private List<String> flags;

    @CsExpose
    private ContactAddress address;

    @CsExpose
    private ContactPhone phone;

    @CsExpose
    private Email email;

    /**
     * Contact ID
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Type of contact. ENUM values: ADDRESS, PHONE, EMAIL, FAX.
     *
     * @return the type
     */
    public ContactType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(ContactType.class, typeRaw);
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
     * Contact flags
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Contact address
     *
     * @return the address
     */
    public ContactAddress getAddress() {
        return address;
    }

    /**
     * Contact phone
     *
     * @return the phone
     */
    public ContactPhone getPhone() {
        return phone;
    }

    /**
     * Primary contact email address
     *
     * @return the email
     */
    public Email getEmail() {
        return email;
    }
}

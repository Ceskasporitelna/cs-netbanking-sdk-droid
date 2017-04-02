package cz.csas.netbanking.phoneNumbers;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiRequest;

/**
 * The type Phone number update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class PhoneNumberUpdateRequest extends WebApiRequest {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    @CsExpose
    private String phoneNumber;

    @CsExpose
    private List<String> flags;

    public PhoneNumberUpdateRequest(String alias, String phoneNumber, List<String> flags) {
        this.alias = alias;
        this.phoneNumber = phoneNumber;
        this.flags = flags;
    }

    /**
     * Phone book entry identifier.
     *
     * @param id the id
     */
     void setId(String id) {
        this.id = id;
    }

    /**
     * Alias name of phone number entered by user for his better orientation in phone book.
     *
     * @return the alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Alias name of phone number entered by user for his better orientation in phone book.
     *
     * @param alias the alias
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Phone number which will be saved in phone book. The value in the phone number field must be
     * a 9-digit number that cannot have a leading zero.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Phone number which will be saved in phone book. The value in the phone number field must be
     * a 9-digit number that cannot have a leading zero.
     *
     * @param phoneNumber the phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Array of optional Flag values.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Array of optional Flag values.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}

package cz.csas.netbanking.phoneNumbers;

import java.util.List;

import cz.csas.cscore.client.rest.CallbackWebApi;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEmptyResponse;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Phone number.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class PhoneNumber extends WebApiEntity {

    @CsExpose
    private String id;

    @CsExpose
    private String alias;

    @CsExpose
    private String phoneNumber;

    @CsExpose
    private List<String> flags;

    /**
     * Phone book entry identifier.
     *
     * @return the id
     */
    public String getId() {
        return id;
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
     * Phone number which will be saved in phone book. The value in the phone number field must be
     * a 9-digit number that cannot have a leading zero.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Convenience method for deleting Phone number
     *
     * @param callback the callback
     */
    public void delete(CallbackWebApi<WebApiEmptyResponse> callback) {
        if (resource instanceof PhoneNumberResource)
            ((PhoneNumberResource) resource).delete(callback);
    }

    /**
     * Convenience method for updating Phone number
     *
     * @param request  the request
     * @param callback the callback
     */
    public void update(PhoneNumberUpdateRequest request, CallbackWebApi<PhoneNumber> callback) {
        if (resource instanceof PhoneNumberResource)
            ((PhoneNumberResource) resource).update(request, callback);
    }
}
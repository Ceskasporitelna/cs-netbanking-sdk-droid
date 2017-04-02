package cz.csas.netbanking.phoneNumbers;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.PaginatedListResponse;

/**
 * The type Phone numbers list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 14.07.16.
 */
public class PhoneNumbersListResponse extends PaginatedListResponse<PhoneNumber, PhoneNumbersListResponse> {

    @CsExpose
    @CsSerializedName(value = "phoneNumbers", alternate = "items")
    private List<PhoneNumber> phoneNumbers;

    @Override
    protected List<PhoneNumber> getItems() {
        return phoneNumbers;
    }

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }
}

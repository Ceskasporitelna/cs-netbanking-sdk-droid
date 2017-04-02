package cz.csas.netbanking.contacts;

import java.util.List;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.ListResponse;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Contacts list response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 26.07.16.
 */
public class ContactsListResponse extends ListResponse<Contact> {

    @CsExpose
    @CsSerializedName(value = "contacts", alternate = "items")
    private List<Contact> contacts;

    @Override
    protected List<Contact> getItems() {
        return contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}

package cz.csas.netbanking.contracts.insurances;

import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.contacts.ContactAddress;

/**
 * The type Insuree.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class Insuree extends WebApiEntity {

    private InsureeType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String id;

    @CsExpose
    private String name;

    @CsExpose
    private List<ContactAddress> addresses;

    @CsExpose
    private String birthnumber;

    @CsExpose
    private String phoneNumber;

    @CsExpose
    private String email;

    @CsExpose
    private List<Risk> risks;

    /**
     * Type of person related to the insurance contract. 3 possible values: POLICYHOLDER, INSURED_PERSON, CHILD.
     *
     * @return the type
     */
    public InsureeType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(InsureeType.class, typeRaw);
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
     * Unique ID of the person related to the insurance contract. ID is hashed combination of contract number and birthnumber of the person: contractNumber_birthnumber.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Name of the person related to the insurance contract.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Contact addresses.
     *
     * @return the addresses
     */
    public List<ContactAddress> getAddresses() {
        return addresses;
    }

    /**
     * Birthnumber of the person related to the insurance contract.
     *
     * @return the birth number
     */
    public String getBirthNumber() {
        return birthnumber;
    }

    /**
     * Phone number of the person related to the insurance contract.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Email address of the person related to the insurance contract.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Risks information
     *
     * @return the risks
     */
    public List<Risk> getRisks() {
        return risks;
    }
}

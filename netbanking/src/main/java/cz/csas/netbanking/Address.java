package cz.csas.netbanking;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Address provides information about Address.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Address {

    @CsExpose
    private String street;

    @CsExpose
    private String streetNumber;

    @CsExpose
    private String buildingApartment;

    @CsExpose
    private String zipCode;

    @CsExpose
    private String city;

    @CsExpose
    private String country;

    @CsExpose
    private String description;

    /**
     * Get street of the address.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Get number which is unique in street. Not all localities have streets.
     *
     * @return the street number
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Get number which is unique in locality/town/village.
     *
     * @return the building apartment
     */
    public String getBuildingApartment() {
        return buildingApartment;
    }

    /**
     * Get zip code.
     *
     * @return the zip code
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Get city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Get country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get more detailed description of address, company name or department.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }
}

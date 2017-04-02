package cz.csas.netbanking.cards;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Card characteristic.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 29 /03/16.
 */
public enum CardCharacteristic implements HasValue {

    /**
     * Main card characteristic.
     */
    MAIN("MAIN"),

    /**
     * Authorized card characteristic.
     */
    AUTHORIZED("AUTHORIZED"),

    /**
     * Other card characteristic.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Card characteristic.
     *
     * @param value the value
     */
    CardCharacteristic(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}

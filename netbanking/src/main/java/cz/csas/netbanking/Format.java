package cz.csas.netbanking;

import cz.csas.cscore.webapi.HasValue;

/**
 * The enum Format.
 */
public enum Format implements HasValue {

    /**
     * Pdf a 4 format.
     */
    PDF_A4("PDF_A4"),

    /**
     * Other format.
     */
    OTHER(null);

    private String value;

    /**
     * Instantiates a new Format.
     *
     * @param value the value
     */
    Format(String value) {
        this.value = value;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    @Override
    public String getValue() {
        return value;
    }

}

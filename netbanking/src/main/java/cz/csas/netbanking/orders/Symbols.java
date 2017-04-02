package cz.csas.netbanking.orders;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;

/**
 * The type Symbols.
 *
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 27 /03/16.
 */
public class Symbols {

    @CsExpose
    private String variableSymbol;

    @CsExpose
    private String constantSymbol;

    @CsExpose
    private String specificSymbol;

    public Symbols(String variableSymbol, String constantSymbol, String specificSymbol) {
        this.variableSymbol = variableSymbol;
        this.constantSymbol = constantSymbol;
        this.specificSymbol = specificSymbol;
    }

    /**
     * Variable symbol.
     *
     * @return the variable symbol
     */
    public String getVariableSymbol() {
        return variableSymbol;
    }

    /**
     * Constant symbol
     *
     * @return the constant symbol
     */
    public String getConstantSymbol() {
        return constantSymbol;
    }

    /**
     * Specific symbol
     *
     * @return the specific symbol
     */
    public String getSpecificSymbol() {
        return specificSymbol;
    }

    /**
     * Variable symbol.
     *
     * @param variableSymbol the variable symbol
     */
    public void setVariableSymbol(String variableSymbol) {
        this.variableSymbol = variableSymbol;
    }

    /**
     * Constant symbol.
     *
     * @param constantSymbol the constant symbol
     */
    public void setConstantSymbol(String constantSymbol) {
        this.constantSymbol = constantSymbol;
    }

    /**
     * Specific symbol.
     *
     * @param specificSymbol the specific symbol
     */
    public void setSpecificSymbol(String specificSymbol) {
        this.specificSymbol = specificSymbol;
    }
}

package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.orders.Symbols;

/**
 * The type Payment template.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 13.07.16.
 */
public class PaymentTemplate extends WebApiEntity {

    private PaymentTemplateType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private Symbols symbols;

    @CsExpose
    private AccountNumber receiver;

    /**
     * Type of payment template. Possible values - ORDINARY, EXTRAORDINARY
     *
     * @return the type
     */
    public PaymentTemplateType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(PaymentTemplateType.class, typeRaw);
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
     * Payment symbols
     *
     * @return the symbols
     */
    public Symbols getSymbols() {
        return symbols;
    }

    /**
     * Receiver account number
     *
     * @return the receiver
     */
    public AccountNumber getReceiver() {
        return receiver;
    }
}

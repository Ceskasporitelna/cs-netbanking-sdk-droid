package cz.csas.netbanking.contracts.insurances;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiRequest;
import cz.csas.netbanking.AccountNumber;
import cz.csas.netbanking.Amount;

/**
 * The type Insurance transfer update request.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 31.08.16.
 */
public class InsuranceTransferUpdateRequest extends WebApiRequest {

    private InsuranceTransferType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private Amount amount;

    @CsExpose
    private AccountNumber sender;

    /**
     * Instantiates a new Insurance transfer update request.
     *
     * @param type   the type
     * @param amount the amount
     * @param sender the sender
     */
    public InsuranceTransferUpdateRequest(InsuranceTransferType type, Amount amount, AccountNumber sender) {
        this.typeRaw = type.getValue();
        this.amount = amount;
        this.sender = sender;
    }

    /**
     * Instantiates a new Insurance transfer update request.
     *
     * @param type   the type
     * @param amount the amount
     * @param sender the sender
     */
    public InsuranceTransferUpdateRequest(String type, Amount amount, AccountNumber sender) {
        this.typeRaw = type;
        this.amount = amount;
        this.sender = sender;
    }

    /**
     * Type of the transfer. Possible values are PAY_PREMIUM, EXTRA_DEPOSIT, RECOMMENDED_DEPOSIT.
     *
     * @return the type
     */
    public InsuranceTransferType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(InsuranceTransferType.class, typeRaw);
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
     * Type of the transfer. Possible values are PAY_PREMIUM, EXTRA_DEPOSIT, RECOMMENDED_DEPOSIT.
     *
     * @param type the type
     */
    public void setType(InsuranceTransferType type) {
        this.typeRaw = type.getValue();
    }

    /**
     * Type of the transfer. Possible values are PAY_PREMIUM, EXTRA_DEPOSIT, RECOMMENDED_DEPOSIT.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.typeRaw = type;
    }

    /**
     * Amount which should be transfered.
     *
     * @return the amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Amount which should be transfered.
     *
     * @param amount the amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    /**
     * Sender account.
     *
     * @return the sender
     */
    public AccountNumber getSender() {
        return sender;
    }

    /**
     * Sender account.
     *
     * @param sender the sender
     */
    public void setSender(AccountNumber sender) {
        this.sender = sender;
    }
}

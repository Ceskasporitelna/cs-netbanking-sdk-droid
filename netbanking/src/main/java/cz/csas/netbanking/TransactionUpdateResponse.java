package cz.csas.netbanking;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.cscore.webapi.apiquery.CsSignUrl;
import cz.csas.cscore.webapi.signing.Signable;
import cz.csas.cscore.webapi.signing.SigningObject;

/**
 * The type Account transaction update response.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
@CsSignUrl()
public class TransactionUpdateResponse extends WebApiEntity implements Signable {

    private SigningObject signingObject;

    /**
     * The transaction
     */
    @CsExpose
    @CsSerializedName(value = "transaction", alternate = {"cardTransaction"})
    private Transaction transaction;

    /**
     * Gets transaction.
     *
     * @return the transaction
     */
    public Transaction getTransaction() {
        return transaction;
    }

    @Override
    public SigningObject signing() {
        return signingObject;
    }

    @Override
    public void setSigningObject(SigningObject signingObject) {
        this.signingObject = signingObject;
    }
}

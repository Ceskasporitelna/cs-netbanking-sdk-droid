package cz.csas.netbanking;

import cz.csas.cscore.error.CsSDKError;

/**
 * The type Cs netbanking error.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class CsNetbankingError extends CsSDKError {

    /**
     * The enum Kind.
     */
    public enum Kind {

        /**
         * The Bad initialization.
         */
        BAD_INITIALIZATION("Bad netbanking initialization. WebApiKey or Environment is missing.");

        private String detailedMessage;

        Kind(String detailedMessage) {
            this.detailedMessage = detailedMessage;
        }

    }

    private final Kind kind;

    /**
     * Instantiates a new Cs netbanking error.
     *
     * @param kind the kind
     */
    public CsNetbankingError(Kind kind) {
        super(kind.detailedMessage);
        this.kind = kind;
    }

    /**
     * Instantiates a new Cs netbanking error.
     *
     * @param kind      the kind
     * @param throwable the throwable
     */
    public CsNetbankingError(Kind kind, Throwable throwable) {
        super(throwable);
        this.kind = kind;
    }

    /**
     * Instantiates a new Cs netbanking error.
     *
     * @param kind          the kind
     * @param detailMessage the detail message
     */
    public CsNetbankingError(Kind kind, String detailMessage) {
        super(detailMessage);
        this.kind = kind;
    }

    /**
     * Gets kind.
     *
     * @return the kind
     */
    public Kind getKind() {
        return kind;
    }
}

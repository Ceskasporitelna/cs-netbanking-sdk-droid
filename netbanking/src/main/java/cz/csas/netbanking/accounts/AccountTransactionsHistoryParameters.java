package cz.csas.netbanking.accounts;

import java.util.Date;
import java.util.Map;

import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.webapi.PaginatedParameters;
import cz.csas.cscore.webapi.Pagination;

/**
 * @author Jan Hauser <jan.hauser@applifting.cz>
 * @since 22/01/2018.
 */
public class AccountTransactionsHistoryParameters extends PaginatedParameters {

    private final String PARAM_DATE_START = "dateStart";
    private final String PARAM_DATE_END = "dateEnd";

    private final Date dateStart;
    private final Date dateEnd;

    public AccountTransactionsHistoryParameters(Builder builder) {
        super(builder.pagination);
        this.dateStart = builder.dateStart;
        this.dateEnd = builder.dateEnd;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> dictionary = super.toDictionary();
        if (dateStart != null)
            dictionary.put(PARAM_DATE_START, TimeUtils.getISO8601String(dateStart));
        if (dateEnd != null)
            dictionary.put(PARAM_DATE_END, TimeUtils.getISO8601String(dateEnd));
        return dictionary;
    }

    /**
     * Transactions from Example: 2014-06-01T00:00:00+02:00
     *
     * @return date transaction history is taken from
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Transactions to Example: 2014-06-30T00:00:00+02:00
     *
     * @return date transaction history is taken to
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    public static class Builder {
        private Pagination pagination;
        private Date dateStart;
        private Date dateEnd;

        /**
         * @param pagination for transactions history
         * @return builder
         */
        public Builder setPagination(Pagination pagination) {
            this.pagination = pagination;
            return this;
        }

        /**
         * Transactions from Example: 2014-06-01T00:00:00+02:00
         *
         * @param dateStart transactions are taken from
         * @return builder
         */
        public Builder setDateStart(Date dateStart) {
            this.dateStart = dateStart;
            return this;
        }

        /**
         * Transactions to Example: 2014-06-30T00:00:00+02:00
         *
         * @param dateEnd transactions are taken to
         * @return builder
         */
        public Builder setDateEnd(Date dateEnd) {
            this.dateEnd = dateEnd;
            return this;
        }

        /**
         * Build account transactions parameters
         *
         * @return parameters
         */
        public AccountTransactionsHistoryParameters build() {
            return new AccountTransactionsHistoryParameters(this);
        }
    }
}

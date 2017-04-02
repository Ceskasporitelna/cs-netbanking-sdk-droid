package cz.csas.netbanking;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.UrlUtils;
import cz.csas.cscore.webapi.Parameters;

/**
 * The type Transactions export parameters.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 08.04.16.
 */
public class ExportTransactionsParameters extends Parameters {

    private final String PARAM_FIELDS = "fields";
    private final String PARAM_SHOW_ACCOUNT_NAME = "showAccountName";
    private final String PARAM_SHOW_ACCOUNT_NUMBER = "showAccountNumber";
    private final String PARAM_SHOW_TIMESPAN = "showTimespan";
    private final String PARAM_SHOW_BALANCE = "showBalance";
    private final String PARAM_DATE_FROM = "dateFrom";
    private final String PARAM_DATE_TO = "dateTo";

    private List<TransactionField> fields;

    private final List<String> fieldsRaw;

    private final Boolean showAccountName;

    private final Boolean showAccountNumber;

    private final Boolean showTimespan;

    private final Boolean showBalance;

    private final Date dateFrom;

    private final Date dateTo;

    private ExportTransactionsParameters(Builder builder) {
        this.fieldsRaw = builder.fields;
        this.showAccountName = builder.showAccountName;
        this.showAccountNumber = builder.showAccountNumber;
        this.showTimespan = builder.showTimespan;
        this.showBalance = builder.showBalance;
        this.dateFrom = builder.dateFrom;
        this.dateTo = builder.dateTo;
    }

    @Override
    public Map<String, String> toDictionary() {
        Map<String, String> dictionary = super.toDictionary();

        if (fieldsRaw != null && fieldsRaw.size() != 0)
            dictionary.put(PARAM_FIELDS, UrlUtils.joinStrings(fieldsRaw));
        if (getShowAccountName() != null)
            dictionary.put(PARAM_SHOW_ACCOUNT_NAME, getShowAccountName().toString());
        if (getShowAccountNumber() != null)
            dictionary.put(PARAM_SHOW_ACCOUNT_NUMBER, getShowAccountNumber().toString());
        if (getShowTimespan() != null)
            dictionary.put(PARAM_SHOW_TIMESPAN, getShowTimespan().toString());
        if (getShowBalance() != null)
            dictionary.put(PARAM_SHOW_BALANCE, getShowBalance().toString());
        if (dateFrom != null)
            dictionary.put(PARAM_DATE_FROM, TimeUtils.getISO8601String(dateFrom));
        if (dateTo != null)
            dictionary.put(PARAM_DATE_TO, TimeUtils.getISO8601String(dateTo));
        return dictionary;
    }

    /**
     * Get list of fields in the response.
     *
     * @return the fields
     */
    public List<TransactionField> getFields() {
        if (fields == null && fieldsRaw != null) {
            fields = new ArrayList<>();
            for (String field : fieldsRaw) {
                TransactionField fieldEnum = EnumUtils.translateToEnum(TransactionField.class, field);
                if (!fields.contains(fieldEnum))
                    fields.add(fieldEnum);
            }
        }
        return fields;
    }

    /**
     * Get fields raw.
     *
     * @return the fields raw
     */
    public List<String> getFieldsRaw() {
        return fieldsRaw;
    }

    /**
     * Indication whether account name should be visible in export. Default is false.
     *
     * @return the show account name
     */
    public Boolean getShowAccountName() {
        return showAccountName;
    }

    /**
     * Indication whether account number should be visible in export. Default is false.
     *
     * @return the show account number
     */
    public Boolean getShowAccountNumber() {
        return showAccountNumber;
    }

    /**
     * Indication whether timespan of the export should be visible. Default is false.
     *
     * @return the show timespan
     */
    public Boolean getShowTimespan() {
        return showTimespan;
    }

    /**
     * Indication whether balance of the account should be visible in export. Default is false.
     *
     * @return the show balance
     */
    public Boolean getShowBalance() {
        return showBalance;
    }

    /**
     * Get date from which transactions should be exported.
     *
     * @return the date from
     */
    public Date getDateFrom() {
        return dateFrom;
    }

    /**
     * Get date to which transactions should be exported.
     *
     * @return the date to
     */
    public Date getDateTo() {
        return dateTo;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private List<String> fields;
        private Boolean showAccountName;
        private Boolean showAccountNumber;
        private Boolean showTimespan;
        private Boolean showBalance;
        private Date dateFrom;
        private Date dateTo;

        /**
         * Set list of fields.
         * It accepts {@link List<TransactionField>} or either {@link List<String>}
         *
         * @param fields the fields
         * @return the fields
         */
        public Builder setFields(List fields) {
            if (fields != null) {
                if (this.fields == null)
                    this.fields = new ArrayList<>();
                for (int i = 0; i < fields.size(); i++) {
                    Object field = fields.get(i);
                    if (field instanceof TransactionField)
                        this.fields.add(((TransactionField) field).getValue());
                    else if (field instanceof String)
                        this.fields.add((String) field);
                }
            }
            return this;
        }

        /**
         * Set whether account name should be visible in export. Default is false.
         *
         * @param showAccountName the show account name
         * @return the show account name
         */
        public Builder setShowAccountName(Boolean showAccountName) {
            this.showAccountName = showAccountName;
            return this;
        }

        /**
         * Set whether account number should be visible in export. Default is false.
         *
         * @param showAccountNumber the show account number
         * @return the show account number
         */
        public Builder setShowAccountNumber(Boolean showAccountNumber) {
            this.showAccountNumber = showAccountNumber;
            return this;
        }

        /**
         * Set whether timespan of the export should be visible. Default is false.
         *
         * @param showTimespan the show timespan
         * @return the show timespan
         */
        public Builder setShowTimespan(Boolean showTimespan) {
            this.showTimespan = showTimespan;
            return this;
        }

        /**
         * Set whether balance of the account should be visible in export. Default is false.
         *
         * @param showBalance the show balance
         * @return the show balance
         */
        public Builder setShowBalance(Boolean showBalance) {
            this.showBalance = showBalance;
            return this;
        }

        /**
         * Set date from which transactions should be exported.
         *
         * @param dateFrom the date from
         * @return the date from
         */
        public Builder setDateFrom(Date dateFrom) {
            this.dateFrom = dateFrom;
            return this;
        }

        /**
         * Set date to which transactions should be exported.
         *
         * @param dateTo the date to
         * @return the date to
         */
        public Builder setDateTo(Date dateTo) {
            this.dateTo = dateTo;
            return this;
        }

        /**
         * Build transactions export parameters.
         *
         * @return the transactions export parameters
         */
        public ExportTransactionsParameters build() {
            return new ExportTransactionsParameters(this);
        }
    }

}

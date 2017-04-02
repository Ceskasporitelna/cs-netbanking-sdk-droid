package cz.csas.netbanking.contracts.insurances;

import java.util.Date;
import java.util.List;

import cz.csas.cscore.utils.EnumUtils;
import cz.csas.cscore.utils.TimeUtils;
import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.utils.csjson.annotations.CsSerializedName;
import cz.csas.cscore.webapi.WebApiEntity;

/**
 * The type Insurance beneficiary.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 12.07.16.
 */
public class InsuranceBeneficiary extends WebApiEntity {

    private InsuranceBeneficiaryType type;

    @CsExpose
    @CsSerializedName("type")
    private String typeRaw;

    @CsExpose
    private String name;

    @CsExpose
    private String birthdate;

    @CsExpose
    private Double percentage;

    @CsExpose
    private String unstructuredInfo;

    @CsExpose
    private List<String> flags;

    /**
     * Instantiates a new Insurance beneficiary.
     *
     * @param type       the type
     * @param name       the name
     * @param birthDate  the birth date
     * @param percentage the percentage
     */
    public InsuranceBeneficiary(InsuranceBeneficiaryType type, String name, Date birthDate, Double percentage) {
        this.typeRaw = type.getValue();
        this.name = name;
        this.birthdate = TimeUtils.getPlainDateString(birthDate);
        this.percentage = percentage;
    }

    /**
     * Instantiates a new Insurance beneficiary.
     *
     * @param type       the type
     * @param name       the name
     * @param birthdate  the birthdate
     * @param percentage the percentage
     */
    public InsuranceBeneficiary(String type, String name, Date birthdate, Double percentage) {
        this.typeRaw = type;
        this.name = name;
        this.birthdate = TimeUtils.getPlainDateString(birthdate);
        this.percentage = percentage;
    }

    /**
     * Type of beneficiary
     *
     * @return the type
     */
    public InsuranceBeneficiaryType getType() {
        if (type == null && typeRaw != null)
            type = EnumUtils.translateToEnum(InsuranceBeneficiaryType.class, typeRaw);
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
     * Set type of beneficiary
     *
     * @param type the type
     */
    public void setType(InsuranceBeneficiaryType type) {
        this.typeRaw = type.getValue();
    }


    /**
     * Set type of beneficiary
     *
     * @param type the type
     */
    public void setTypeRaw(String type) {
        this.typeRaw = type;
    }

    /**
     * Name of the beneficiary.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Name of the beneficiary.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Birthdate of the beneficiary.
     *
     * @return the birth date
     */
    public Date getBirthdate() {
        return TimeUtils.getPlainDate(birthdate);
    }

    /**
     * Birthdate of the beneficiary.
     *
     * @param birthDate the birth date
     */
    public void setBirthdate(Date birthDate) {
        this.birthdate = TimeUtils.getPlainDateString(birthDate);
    }

    /**
     * Percentage of the insurance contract determined to beneficiary or distributed by law. Value
     * in percentage, e.g. 63 will be displayed as 63 %.
     *
     * @return the percentage
     */
    public Double getPercentage() {
        return percentage;
    }

    /**
     * Percentage of the insurance contract determined to beneficiary or distributed by law. Value
     * in percentage, e.g. 63 will be displayed as 63 %.
     *
     * @param percentage the percentage
     */
    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    /**
     * Attribute returns unstructured information about distribution among beneficiaries in a single
     * string in 2 cases: 1) Distribution have never been changed (after modifying the distribution
     * - information will be returned ina structured form); 2) BE did not cut up unstructured format.
     *
     * @return the unstructured info
     */
    public String getUnstructuredInfo() {
        return unstructuredInfo;
    }

    /**
     * Attribute returns unstructured information about distribution among beneficiaries in a single
     * string in 2 cases: 1) Distribution have never been changed (after modifying the distribution
     * - information will be returned ina structured form); 2) BE did not cut up unstructured format.
     *
     * @param unstructuredInfo the unstructured info
     */
    public void setUnstructuredInfo(String unstructuredInfo) {
        this.unstructuredInfo = unstructuredInfo;
    }

    /**
     * Get list of flags.
     * <p>
     * Possible flags:
     * Flag	                        Description
     * beneficiariesChangeAllowed	Flag indicating possibility to change distribution among beneficiaries.
     * beneficiariesOngoingChange	Flag indicating ongoing change that has been already requested.
     *
     * @return the flags
     */
    public List<String> getFlags() {
        return flags;
    }

    /**
     * Set list of flags.
     * <p>
     * Possible flags:
     * Flag	                        Description
     * beneficiariesChangeAllowed	Flag indicating possibility to change distribution among beneficiaries.
     * beneficiariesOngoingChange	Flag indicating ongoing change that has been already requested.
     *
     * @param flags the flags
     */
    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}

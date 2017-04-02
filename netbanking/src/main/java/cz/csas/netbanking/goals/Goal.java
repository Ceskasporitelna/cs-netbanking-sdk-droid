package cz.csas.netbanking.goals;

import java.util.Date;

import cz.csas.cscore.utils.csjson.annotations.CsExpose;
import cz.csas.cscore.webapi.WebApiEntity;
import cz.csas.netbanking.Amount;

/**
 * The type Goal.
 *
 * @author Frantisek Kratochvil <kratochvilf@gmail.com>
 * @since 27.07.16.
 */
public class Goal extends WebApiEntity {

    @CsExpose
    private String name;

    @CsExpose
    private Amount price;

    @CsExpose
    private Long deadline;

    @CsExpose
    private Boolean completed;

    public Goal(String name, Amount price, Date deadline, Boolean completed) {
        this.name = name;
        this.price = price;
        this.completed = completed;
        setDeadline(deadline);
    }

    /**
     * Saving goal name. Must be non-empty and unique among goals of one client.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Saving goal name. Must be non-empty and unique among goals of one client.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Price of the saving goal.
     *
     * @return the price
     */
    public Amount getPrice() {
        return price;
    }

    /**
     * Price of the saving goal.
     *
     * @param price the price
     */
    public void setPrice(Amount price) {
        this.price = price;
    }

    /**
     * Maximal date (deadline) of the saving goal completion.
     *
     * @return the deadline
     */
    public Date getDeadline() {
        if (this.deadline == null) {
            return null;
        }
        return new Date(this.deadline);
    }

    /**
     * Maximal date (deadline) of the saving goal completion.
     *
     * @param deadline the deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline.getTime();
    }

    /**
     * Flag of the completed goal.
     *
     * @return the completed
     */
    public Boolean getCompleted() {
        return completed;
    }

    /**
     * Flag of the completed goal.
     *
     * @param completed the completed
     */
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}

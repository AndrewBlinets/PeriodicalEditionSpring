package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table(name = "subscription")
public class Subscription extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn (name = "user")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn (name = "camelcase")
    private PeriodicalEdittion periodicalEdittion;


    public Subscription() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PeriodicalEdittion getPeriodicalEdittion() {
        return periodicalEdittion;
    }

    public void setPeriodicalEdittion(PeriodicalEdittion periodicalEdittion) {
        this.periodicalEdittion = periodicalEdittion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Subscription)){
            return false;
        }

        Subscription that = (Subscription) o;

        if (getId() != that.getId()){
            return false;
        }
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null){
            return false;
        }
        return getPeriodicalEdittion() != null ? getPeriodicalEdittion().equals(that.getPeriodicalEdittion()) : that.getPeriodicalEdittion() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getPeriodicalEdittion() != null ? getPeriodicalEdittion().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", periodicalEdittion=").append(periodicalEdittion);
        sb.append('}');
        return sb.toString();
    }
}

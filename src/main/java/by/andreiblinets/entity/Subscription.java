package by.andreiblinets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Subscription")
public class Subscription {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @ManyToOne
    @JoinColumn (name = "id")
    private User user;

    @OneToMany
    @JoinColumn (name = "id")
    private PeriodicalEdition periodicalEdition;


    public Subscription() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PeriodicalEdition getPeriodicalEdition() {
        return periodicalEdition;
    }

    public void setPeriodicalEdition(PeriodicalEdition periodicalEdition) {
        this.periodicalEdition = periodicalEdition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Subscription)) return false;

        Subscription that = (Subscription) o;

        if (getId() != that.getId()) return false;
        if (getUser() != null ? !getUser().equals(that.getUser()) : that.getUser() != null) return false;
        return getPeriodicalEdition() != null ? getPeriodicalEdition().equals(that.getPeriodicalEdition()) : that.getPeriodicalEdition() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getPeriodicalEdition() != null ? getPeriodicalEdition().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", periodicalEdition=").append(periodicalEdition);
        sb.append('}');
        return sb.toString();
    }
}

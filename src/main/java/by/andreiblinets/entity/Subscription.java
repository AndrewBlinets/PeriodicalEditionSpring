package by.andreiblinets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Subscription")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn (name = "id")
    private User user;

    @OneToMany
    @JoinColumn (name = "id")
    private List<PeriodicalEdition> periodicalEditions;


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

    public List<PeriodicalEdition> getPeriodicalEditions() {
        return periodicalEditions;
    }

    public void setPeriodicalEditions(List<PeriodicalEdition> periodicalEditions) {
        this.periodicalEditions = periodicalEditions;
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
        return getPeriodicalEditions() != null ? getPeriodicalEditions().equals(that.getPeriodicalEditions()) : that.getPeriodicalEditions() == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = (int) this.getId() + 2;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", periodicalEditions=").append(periodicalEditions);
        sb.append('}');
        return sb.toString();
    }
}

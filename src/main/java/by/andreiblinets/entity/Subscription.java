package by.andreiblinets.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subscription")
public class Subscription implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name = "iduser")
    private User user;

    @ManyToOne
    @JoinColumn (name = "idcamelcase")
    private CamelCase camelCase;


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

    public CamelCase getCamelCase() {
        return camelCase;
    }

    public void setCamelCase(CamelCase camelCase) {
        this.camelCase = camelCase;
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
        return getCamelCase() != null ? getCamelCase().equals(that.getCamelCase()) : that.getCamelCase() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getCamelCase() != null ? getCamelCase().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Subscription{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", camelCase=").append(camelCase);
        sb.append('}');
        return sb.toString();
    }
}

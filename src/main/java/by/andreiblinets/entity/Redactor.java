package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table (name = "redactor")
public class Redactor extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "camelcase")
    private CamelCase camelCase;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "user")
    private User user;

    public Redactor() {
    }

    public CamelCase getCamelCase() {
        return camelCase;
    }

    public void setCamelCase(CamelCase camelCase) {
        this.camelCase = camelCase;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Redactor)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Redactor redactor = (Redactor) o;

        if (getCamelCase() != null ? !getCamelCase().equals(redactor.getCamelCase()) : redactor.getCamelCase() != null)
        {
            return false;
        }
        return getUser() != null ? getUser().equals(redactor.getUser()) : redactor.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getCamelCase() != null ? getCamelCase().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Redactor{");
        sb.append("camelCase=").append(camelCase);
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

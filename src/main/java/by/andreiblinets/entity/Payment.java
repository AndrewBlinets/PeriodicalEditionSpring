package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

    @ManyToOne
    @JoinColumn (name = "iduser")
    private User user;

    @Column (name = "quittance")
    private String quittance;

    public Payment() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getQuittance() {
        return quittance;
    }

    public void setQuittance(String quittance) {
        this.quittance = quittance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Payment)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Payment payment = (Payment) o;

        if (getUser() != null ? !getUser().equals(payment.getUser()) : payment.getUser() != null){
            return false;
        }
        return getQuittance() != null ? getQuittance().equals(payment.getQuittance()) : payment.getQuittance() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getQuittance() != null ? getQuittance().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("user=").append(user);
        sb.append(", quittance='").append(quittance).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

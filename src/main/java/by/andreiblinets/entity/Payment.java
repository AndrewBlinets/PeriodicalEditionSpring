package by.andreiblinets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "payment")
public class Payment extends AbstractEntity {

    @ManyToOne
    @JoinColumn (name = "iduser")
    private User user;

    @Column (name = "summa")
    private double summa;

    public Payment() {
        super();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Payment)){
            return false;
        }

        Payment payment = (Payment) o;

        if (getId() != payment.getId()){
            return false;
        }
        if (Double.compare(payment.getSumma(), getSumma()) != 0){
            return false;
        }
        return getUser() != null ? getUser().equals(payment.getUser()) : payment.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = (int) this.getId() + 2;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Payment{");
        sb.append("id=").append(id);
        sb.append(", user=").append(user);
        sb.append(", summa=").append(summa);
        sb.append('}');
        return sb.toString();
    }
}

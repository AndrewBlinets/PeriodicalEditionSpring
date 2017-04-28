package by.andreiblinets.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PeriodicalEdition")
public class PeriodicalEdition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private double price;


    public PeriodicalEdition(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public PeriodicalEdition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof PeriodicalEdition)){
            return false;
        }

        PeriodicalEdition that = (PeriodicalEdition) o;

        if (getId() != that.getId()){
            return false;
        }
        if (Double.compare(that.getPrice(), getPrice()) != 0){
            return false;
        }
        return getName() != null ? getName().equals(that.getName()) : that.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = (int) this.getId() + 2;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeriodicalEdition{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}

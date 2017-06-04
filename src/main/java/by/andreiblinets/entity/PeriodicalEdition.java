package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table(name = "periodicaledition")
public class PeriodicalEdition extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private long price;


    public PeriodicalEdition() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PeriodicalEdition)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        PeriodicalEdition periodicalEdition = (PeriodicalEdition) o;

        if (getPrice() != periodicalEdition.getPrice()){
            return false;
        }
        return getName() != null ? getName().equals(periodicalEdition.getName()) : periodicalEdition.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (int) (getPrice() ^ (getPrice() >>> 32));
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeriodicalEdition{");
        sb.append("name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

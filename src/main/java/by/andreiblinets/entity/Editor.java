package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table (name = "editor")
public class Editor extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn (name = "periodicaledition")
    private PeriodicalEdition periodicalEdition;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn (name = "user")
    private User user;

    public Editor() {
    }

    public PeriodicalEdition getPeriodicalEdition() {
        return periodicalEdition;
    }

    public void setPeriodicalEdition(PeriodicalEdition periodicalEdition) {
        this.periodicalEdition = periodicalEdition;
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
        if (!(o instanceof Editor)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Editor editor = (Editor) o;

        if (getPeriodicalEdition() != null ? !getPeriodicalEdition().equals(editor.getPeriodicalEdition()) : editor.getPeriodicalEdition() != null)
        {
            return false;
        }
        return getUser() != null ? getUser().equals(editor.getUser()) : editor.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPeriodicalEdition() != null ? getPeriodicalEdition().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Editor{");
        sb.append("periodicalEdition=").append(periodicalEdition);
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

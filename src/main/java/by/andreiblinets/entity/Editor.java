package by.andreiblinets.entity;

import javax.persistence.*;

@Entity
@Table (name = "redactor")
public class Editor extends AbstractEntity {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "camelcase")
    private PeriodicalEdittion periodicalEdittion;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn (name = "user")
    private User user;

    public Editor() {
    }

    public PeriodicalEdittion getPeriodicalEdittion() {
        return periodicalEdittion;
    }

    public void setPeriodicalEdittion(PeriodicalEdittion periodicalEdittion) {
        this.periodicalEdittion = periodicalEdittion;
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

        if (getPeriodicalEdittion() != null ? !getPeriodicalEdittion().equals(editor.getPeriodicalEdittion()) : editor.getPeriodicalEdittion() != null)
        {
            return false;
        }
        return getUser() != null ? getUser().equals(editor.getUser()) : editor.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getPeriodicalEdittion() != null ? getPeriodicalEdittion().hashCode() : 0);
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Editor{");
        sb.append("periodicalEdittion=").append(periodicalEdittion);
        sb.append(", id=").append(id);
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

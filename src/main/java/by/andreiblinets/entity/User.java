package by.andreiblinets.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column (name = "userRole", nullable = false)
    private String userRole;

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn (name = "idAccount")
    private Account account;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        User user = (User) o;

        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null){
            return false;
        }
        if (getSurname() != null ? !getSurname().equals(user.getSurname()) : user.getSurname() != null){
            return false;
        }
        if (getUserRole() != null ? !getUserRole().equals(user.getUserRole()) : user.getUserRole() != null)
        {
            return false;
        }
        return getAccount() != null ? getAccount().equals(user.getAccount()) : user.getAccount() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        result = 31 * result + (getUserRole() != null ? getUserRole().hashCode() : 0);
        result = 31 * result + (getAccount() != null ? getAccount().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", userRole='").append(userRole).append('\'');
        sb.append(", id=").append(id);
        sb.append(", account=").append(account);
        sb.append('}');
        return sb.toString();
    }
}

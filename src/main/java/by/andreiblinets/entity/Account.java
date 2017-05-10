package by.andreiblinets.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "hashpassword", nullable = false)
    private String hashpassword;

    @OneToOne (mappedBy = "account")
    private User user;

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHashpassword() {
        return hashpassword;
    }

    public void setHashpassword(String hashpassword) {
        this.hashpassword = hashpassword;
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
        if (!(o instanceof Account)){
            return false;
        }

        Account account = (Account) o;

        if (getId() != account.getId()){
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(account.getLogin()) : account.getLogin() != null){
            return false;
        }
        if (getHashpassword() != null ? !getHashpassword().equals(account.getHashpassword()) : account.getHashpassword() != null)
        {
            return false;
        }
        return getUser() != null ? getUser().equals(account.getUser()) : account.getUser() == null;
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = (int) this.getId() + 2;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", hashpassword='").append(hashpassword).append('\'');
        sb.append(", user=").append(user);
        sb.append('}');
        return sb.toString();
    }
}

package by.andreiblinets.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account extends AbstractEntity  {

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "hashpassword", nullable = false)
    private String hashpassword;


    public Account() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof Account)){
            return false;
        }
        if (!super.equals(o)){
            return false;
        }

        Account account = (Account) o;

        if (getLogin() != null ? !getLogin().equals(account.getLogin()) : account.getLogin() != null){
            return false;
        }
        return getHashpassword() != null ? getHashpassword().equals(account.getHashpassword()) : account.getHashpassword() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getHashpassword() != null ? getHashpassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Account{");
        sb.append("login='").append(login).append('\'');
        sb.append(", hashpassword='").append(hashpassword).append('\'');
        sb.append(", id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}

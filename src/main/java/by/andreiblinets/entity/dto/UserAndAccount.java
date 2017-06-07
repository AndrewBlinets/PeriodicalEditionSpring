package by.andreiblinets.entity.dto;

public class UserAndAccount {
    private String login;
    private String password;
    private String oldPassword;
    private String name;
    private String surname;

    public UserAndAccount() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof UserAndAccount)){
            return false;
        }

        UserAndAccount that = (UserAndAccount) o;

        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null){
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
        {
            return false;
        }
        if (getOldPassword() != null ? !getOldPassword().equals(that.getOldPassword()) : that.getOldPassword() != null)
        {
            return false;
        }
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null){
            return false;
        }
        return getSurname() != null ? getSurname().equals(that.getSurname()) : that.getSurname() == null;
    }

    @Override
    public int hashCode() {
        int result = getLogin() != null ? getLogin().hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getOldPassword() != null ? getOldPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserAndAccount{");
        sb.append("login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", oldPassword='").append(oldPassword).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

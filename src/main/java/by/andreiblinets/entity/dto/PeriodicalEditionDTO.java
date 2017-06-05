package by.andreiblinets.entity.dto;

public class PeriodicalEditionDTO {
    private String namePeriodicalEdition;
    private long price;
    private String login;
    private String password;
    private String name;
    private String surname;

    public PeriodicalEditionDTO() {
    }

    public String getNamePeriodicalEdition() {
        return namePeriodicalEdition;
    }

    public void setNamePeriodicalEdition(String namePeriodicalEdition) {
        this.namePeriodicalEdition = namePeriodicalEdition;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof PeriodicalEditionDTO)){
            return false;
        }

        PeriodicalEditionDTO that = (PeriodicalEditionDTO) o;

        if (getPrice() != that.getPrice()){
            return false;
        }
        if (getNamePeriodicalEdition() != null ? !getNamePeriodicalEdition().equals(that.getNamePeriodicalEdition()) : that.getNamePeriodicalEdition() != null)
        {
            return false;
        }
        if (getLogin() != null ? !getLogin().equals(that.getLogin()) : that.getLogin() != null){
            return false;
        }
        if (getPassword() != null ? !getPassword().equals(that.getPassword()) : that.getPassword() != null)
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
        int result = getNamePeriodicalEdition() != null ? getNamePeriodicalEdition().hashCode() : 0;
        result = 31 * result + (int) (getPrice() ^ (getPrice() >>> 32));
        result = 31 * result + (getLogin() != null ? getLogin().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getSurname() != null ? getSurname().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PeriodicalEditionDTO{");
        sb.append("namePeriodicalEdition='").append(namePeriodicalEdition).append('\'');
        sb.append(", price=").append(price);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

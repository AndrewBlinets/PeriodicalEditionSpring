package by.andreiblinets.entity;

import by.andreiblinets.entity.enums.UserRole;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "id")
    private long id;

    @Column(name = "name", length = 32)
    private String name;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column (name = "userRole", nullable = false)
    private UserRole userRole;

    @OneToOne
    @JoinColumn (name = "id")
    private Account account;

    @OneToMany (mappedBy = "user")
    private List<Subscription> subscriptions;

    public User() {
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

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (!(o instanceof User)){
            return false;
        }

        User user = (User) o;

        if (getId() != user.getId()){
            return false;
        }
        if (!getName().equals(user.getName())){
            return false;
        }
        return getUserRole() == user.getUserRole();
    }

    @Override
    public int hashCode() {
        int result = 0;
        result = (int) this.getId() + 2;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", userRole=").append(userRole);
        sb.append(", account=").append(account);
        sb.append(", subscriptions=").append(subscriptions);
        sb.append('}');
        return sb.toString();
    }
}

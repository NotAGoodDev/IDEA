package ucm.gps.idea.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "creator")
public class Creator extends User {

    private static final long serialVersionUID = 1L;

    public Creator() {}

    public Creator(String username, String password, Boolean active, String email , String name,
                   String lastName, Date birthDate, String telephone, String address){
        super(username, password, /*type,*/ active, email, name, address, telephone);
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @OneToMany(mappedBy = "creator")
    private Set<Idea> idea;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Idea> getIdea() {
        return idea;
    }

    public void setIdea(Set<Idea> idea) {
        this.idea = idea;
    }
}
package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "creator")
public class Creator extends User implements Serializable {

    private static final long serialVersionUID = 0;

    public Creator() {}

    public Creator(String username, String password, String type, Boolean active, String email , String name,
                   String lastName, Date birthDate, String telephone, String address){
        super(username, password, type, active, email);
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.telephone = telephone;
        this.address = address;
    }

    @OneToMany(mappedBy = "creator")
    private Set<Idea> idea;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "address")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Idea> getIdea() {
        return idea;
    }

    public void setIdea(Set<Idea> idea) {
        this.idea = idea;
    }
}
package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "creator")
public class Creator implements Serializable {

    private static final long serialVersionUID = 0;

    public Creator() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "creator")
    private Set<Idea> idea;

    @Column(name = "name")
    private String name;

    @Column(name = "last_name")
    private String lastName1;

    @Column(name = "last_name")
    private String lastName2;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName1() {
        return lastName1;
    }

    public void setLastName1(String lastName1) {
        this.lastName1 = lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public void setLastName2(String lastName2) {
        this.lastName2 = lastName2;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Idea> getIdea() {
        return idea;
    }

    public void setIdea(Set<Idea> idea) {
        this.idea = idea;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
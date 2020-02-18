package ucm.gps.idea.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "enterprise")
public class Enterprise implements Serializable {

    private static final long serialVersionUID = 0;

    public Enterprise() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "enterprise")
    private Set<Contract> contract;

    @ManyToOne
    private Category category;

    @Column(name = "name")
    private String name;

    @Column(name = "cif")
    private String CIF;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "credit_card")
    private Integer creditCard;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "remaining_ideas")
    private Integer remainingIdeas;

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

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Integer creditCard) {
        this.creditCard = creditCard;
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

    public Integer getRemainingIdeas() {
        return remainingIdeas;
    }

    public void setRemainingIdeas(Integer remainingIdeas) {
        this.remainingIdeas = remainingIdeas;
    }

    public Set<Contract> getContract() {
        return contract;
    }

    public void setContract(Set<Contract> contract) {
        this.contract = contract;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

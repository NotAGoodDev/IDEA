package ucm.gps.idea.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class RegisterUser {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("email")
    private String email;

    @JsonProperty("type")
    private String type;

    @JsonProperty("active")
    private Boolean active;

    @JsonProperty("name")
    private String name;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birth_date")
    private Date birtDate;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("address")
    private String address;

    @JsonProperty("cif")
    private String cif;

    @JsonProperty("credit_card")
    private Integer creditCard;

    @JsonProperty("reamining_ideas")
    private Integer remainingIdeas;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

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

    public Date getBirtDate() {
        return birtDate;
    }

    public void setBirtDate(Date birtDate) {
        this.birtDate = birtDate;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Integer getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(Integer creditCard) {
        this.creditCard = creditCard;
    }

    public Integer getRemainingIdeas() {
        return remainingIdeas;
    }

    public void setRemainingIdeas(Integer remainingIdeas) {
        this.remainingIdeas = remainingIdeas;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birtDate=" + birtDate +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                ", cif='" + cif + '\'' +
                ", creditCard=" + creditCard +
                ", remainingIdeas=" + remainingIdeas +
                '}';
    }
}

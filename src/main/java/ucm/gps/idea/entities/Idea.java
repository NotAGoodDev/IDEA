package ucm.gps.idea.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "idea", schema = "idea")
public class Idea implements Serializable {

    private static final long serialVersionUID = 0;

    public Idea(){

    }

    public Idea(Deal deal, Creator creator, Enterprise enterprise, Category category, String title, String description, String summary,String state, boolean active) {
        this.deal = deal;   //No necesitamos asignar un deal de primeras
        this.creator = creator;
        this.category = category;
        this.enterprise = enterprise;
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.state = state;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @JsonIgnore
    @OneToOne
    private Deal deal;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Creator creator;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Category category;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Enterprise enterprise;

    /*
    Equivalente a creator
    @Column(name = "user_id")
    private Integer userId;
    */
    @Column(name = "title")
    private String title;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "description")
    private String description;

    @Column(name = "summary")   //Small description
    private String summary;

    @Column(name = "state")
    private String state;

    @Column(name = "active")
    private Boolean active;

    @PrePersist
    public void prePersist(){
        createdAt = new Date(System.currentTimeMillis()); // Idea creada el dia: [hora cogida del sistema]
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Creator getCreator() {
        return creator;
    }

    public void setCreator(Creator creator) {
        this.creator = creator;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Enterprise getEnterprise()
    {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise)
    {
        this.enterprise = enterprise;
    }
}
package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "idea", schema = "idea")
public class Idea implements Serializable {

    private static final long serialVersionUID = 0;

    public Idea(){

    }

    public Idea(Deal deal, Creator creator, Enterprise enterprise, Category category, String title, String description, String summary, boolean active) {
        this.deal = deal;   //No necesitamos asignar un deal de primeras
        this.creator = creator;
        this.category = category;
        this.enterprise = enterprise;
        this.title = title;
        this.description = description;
        this.summary = summary;
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    private Deal deal;

    @ManyToOne
    @JoinColumn
    private Creator creator;

    @ManyToOne
    @JoinColumn
    private Category category;

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

    /*
    Equivalente a creator
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

     */

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

    public void setDescription(String summary) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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
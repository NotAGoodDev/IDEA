package ucm.gps.idea.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 0;

    public Contract() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "contract")
    private Idea idea;

    @ManyToOne
    private Enterprise enterprise;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "data_created")
    private Date dateCreated;

    @Column(name = "date_of_sign")
    private Date dateOfSign;

    @Column(name = "site")
    private String site;

    @Column(name = "percentage")
    private float percentage;


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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateOfSign() {
        return dateOfSign;
    }

    public void setDateOfSign(Date dateOfSign) {
        this.dateOfSign = dateOfSign;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public Idea getIdea() {
        return idea;
    }

    public void setIdea(Idea idea) {
        this.idea = idea;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

}

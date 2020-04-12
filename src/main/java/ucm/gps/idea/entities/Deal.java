package ucm.gps.idea.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "deal")
public class Deal implements Serializable {

    private static final long serialVersionUID = 0;

    public Deal() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToOne(mappedBy = "deal")
    private Idea idea;

    @Column(name = "text")
    private String terms;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "date_signed")
    private Date dateSigned;

    @Column(name = "percentage")
    private float percentage;

    @Column(name = "c_agree")
    private boolean cAgree;

    @Column(name = "e_agree")
    private boolean eAgree;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDateSigned() {
        return dateSigned;
    }

    public void setDateSigned(Date dateSigned) {
        this.dateSigned = dateSigned;
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

    public boolean isCAgree() {
        return cAgree;
    }

    public void setCAgree(boolean cAgree) {
        this.cAgree = cAgree;
    }

    public boolean isEAgree() {
        return eAgree;
    }

    public void setEAgree(boolean eAgree) {
        this.eAgree = eAgree;
    }
}

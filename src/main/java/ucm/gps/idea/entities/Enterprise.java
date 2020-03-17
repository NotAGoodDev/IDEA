package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "enterprise")
public class Enterprise extends User implements Serializable {

    private static final long serialVersionUID = 0;

    public Enterprise(){}

    public Enterprise(String username, String password, String type, Boolean active, String email, String name,
                      String cif, String address, String telephone, Integer creditCard, Integer remIdeas) {
        super(username, password, type, active, email, name, address, telephone);
        this.CIF = cif;
        this.creditCard = creditCard;
        this.remainingIdeas = remIdeas;
    }

    @OneToMany(mappedBy = "enterprise")
    private Set<Deal> deal;

    @OneToMany(mappedBy = "enterprise")
    private Set<Idea> idea;

    @Column(name = "cif")
    private String CIF;

    @Column(name = "credit_card")
    private Integer creditCard;

    @Column(name = "remaining_ideas")
    private Integer remainingIdeas;

    public Set<Deal> getDeal() {
        return deal;
    }

    public void setDeal(Set<Deal> deal) {
        this.deal = deal;
    }

    public Set<Idea> getIdea() {
        return idea;
    }

    public void setIdea(Set<Idea> idea) {
        this.idea = idea;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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
}

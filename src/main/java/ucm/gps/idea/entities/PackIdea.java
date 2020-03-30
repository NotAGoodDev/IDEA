package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pack_idea")
public class PackIdea implements Serializable {

    public PackIdea() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "num_ideas")
    private Integer numIdeas;

    @Column(name = "discount")
    private Double discount;

    @Column(name = "recommended")
    private Boolean recommended;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumIdeas() {
        return numIdeas;
    }

    public void setNumIdeas(Integer numIdeas) {
        this.numIdeas = numIdeas;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getRecommended() {
        return recommended;
    }

    public void setRecommended(Boolean recommended) {
        this.recommended = recommended;
    }

    // En formato JSON por si ayuda en front
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + this.id +
                "\"pack_name\":" + "\"" + this.name + "\"," +
                "\"price\":" + this.price + "," +
                "\"num_ideas\":" + this.numIdeas + "," +
                "\"discount\":" + this.discount + "," +
                "\"recommended\":" + this.recommended + "," +
                "}";
    }
}

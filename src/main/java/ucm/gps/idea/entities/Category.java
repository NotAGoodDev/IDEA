package ucm.gps.idea.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "category")
public class Category implements Serializable{

    private static final long serialVersionUID = 0;

    public Category() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @OneToMany(mappedBy = "category")
    private Set<Idea> idea;

    @OneToMany(mappedBy = "category")
    private Set<Enterprise> enterprise;

    @Column(name = "name")
    private String name;


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

    public Set<Idea> getIdea() {
        return idea;
    }

    public void setIdea(Set<Idea> idea) {
        this.idea = idea;
    }

    public Set<Enterprise> getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Set<Enterprise> enterprise) {
        this.enterprise = enterprise;
    }
}

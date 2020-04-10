package ucm.gps.idea.entities;


import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "admin")
public class Admin extends User implements Serializable {


    public Admin() {
    }

    public Admin(String username, String password, Boolean active, String email, String name, String address, String telephone) {
        super(username, password, active, email, name, address, telephone);
    }

    @OneToMany(mappedBy = "admin")
    private List<Idea> listaIdeas;

    public List<Idea> getListaIdeas() {
        return listaIdeas;
    }

    public void setListaIdeas(List<Idea> listaIdeas) {
        this.listaIdeas = listaIdeas;
    }












}

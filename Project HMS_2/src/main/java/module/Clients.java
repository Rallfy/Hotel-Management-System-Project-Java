package module;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Clients {
    private int idClients;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gendere;
    private Integer cnp;
    private Collection<Rooms> roomsByIdClients;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClients")
    public int getIdClients() {
        return idClients;
    }

    public void setIdClients(int idClients) {
        this.idClients = idClients;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "gendere")
    public String getGendere() {
        return gendere;
    }

    public void setGendere(String gendere) {
        this.gendere = gendere;
    }

    @Basic
    @Column(name = "CNP")
    public Integer getCnp() {
        return cnp;
    }

    public void setCnp(Integer cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return idClients == clients.idClients && Objects.equals(firstName, clients.firstName) && Objects.equals(lastName, clients.lastName) && Objects.equals(age, clients.age) && Objects.equals(gendere, clients.gendere) && Objects.equals(cnp, clients.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClients, firstName, lastName, age, gendere, cnp);
    }

    @OneToMany(mappedBy = "clientsByIdClients")
    public Collection<Rooms> getRoomsByIdClients() {
        return roomsByIdClients;
    }

    public void setRoomsByIdClients(Collection<Rooms> roomsByIdClients) {
        this.roomsByIdClients = roomsByIdClients;
    }
}

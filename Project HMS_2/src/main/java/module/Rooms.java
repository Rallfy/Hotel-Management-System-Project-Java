package module;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Rooms {
    private int idRooms;
    private Integer idClients;
    private Integer numberOfBeds;
    private Date date;
    private String roomStatus;
    private Clients clientsByIdClients;

    @Id
    @Column(name = "idRooms")
    public int getIdRooms() {
        return idRooms;
    }

    public void setIdRooms(int idRooms) {
        this.idRooms = idRooms;
    }

    @Basic
    @Column(name = "idClients")
    public Integer getIdClients() {
        return idClients;
    }

    public void setIdClients(Integer idClients) {
        this.idClients = idClients;
    }

    @Basic
    @Column(name = "numberOfBeds")
    public Integer getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(Integer numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "roomStatus")
    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rooms rooms = (Rooms) o;
        return idRooms == rooms.idRooms && idClients == rooms.idClients && Objects.equals(numberOfBeds, rooms.numberOfBeds) && Objects.equals(date, rooms.date) && Objects.equals(roomStatus, rooms.roomStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRooms, idClients, numberOfBeds, date, roomStatus);
    }

    @ManyToOne
    @JoinColumn(name = "idClients", referencedColumnName = "idClients", nullable = true, insertable = false, updatable = false)
    public Clients getClientsByIdClients() {
        return clientsByIdClients;
    }

    public void setClientsByIdClients(Clients clientsByIdClients) {
        this.clientsByIdClients = clientsByIdClients;
    }

    @Override
    public String toString() {
        return "CAMERA " +
                "numarul " + idRooms +
                ", capacitate " + numberOfBeds;
    }

}

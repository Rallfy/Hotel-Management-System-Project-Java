package service;


import dao.RoomsDao;
import module.Rooms;

import javax.persistence.Persistence;
import java.util.List;

public class RoomsService<T> {
    private RoomsDao roomsDao;
    private Class<T> entityClass;

    public RoomsService() {
        try {
            roomsDao = new RoomsDao(Persistence.createEntityManagerFactory("default"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addRoom(Rooms newRoom) {
        roomsDao.create(newRoom);
    }

    public void updateRoom(Rooms updatedRoom) {
        roomsDao.update(updatedRoom);
    }

    public void removeRoom(Rooms removeRoom, int roomId) {
        roomsDao.remove(removeRoom, removeRoom.getIdRooms());
    }

    public List<Rooms> getAllRoom() {
        return roomsDao.findAll();
    }

    public T find(int id) {
        T x = (T) roomsDao.find(id);
        return x;
    }

}



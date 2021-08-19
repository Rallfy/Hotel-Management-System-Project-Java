package service;

import dao.ClientsDao;
import module.Clients;

import javax.persistence.Persistence;
import java.util.List;

public class ClientsService<T> {
    private ClientsDao clientsDao;
    private Class<T> entityClass;

    public ClientsService() {
        try {
            clientsDao = new ClientsDao(Persistence.createEntityManagerFactory("default"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addClient(Clients newClient) {
        clientsDao.create(newClient);
    }

    public void updateClient(Clients updatedClient) {
        clientsDao.update(updatedClient);
    }

    public void removeClient(Clients removeClient, int clientId) {
        if (clientsDao != null) {
            clientsDao.remove(removeClient, removeClient.getIdClients());
        }
        else System.out.println("Lista(DAO) e NULL");

    }

    public List<Clients> getAllClient() {
        return clientsDao.findAll();
    }

    public T find(int id) {
        T x = (T) clientsDao.find(id);
        return x;
    }

}


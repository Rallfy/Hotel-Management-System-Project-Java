package dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import module.Clients;

public class ClientsDao extends GenericDao<Clients> {

    private EntityManagerFactory factory;

    public ClientsDao(EntityManagerFactory factory) {
        super(Clients.class);
        this.factory = factory;
    }

    @Override
    public EntityManager getEntityManager() {
        try {
            return factory.createEntityManager();
        } catch (Exception ex) {
            System.out.println("The entity manager cannot be created!");
            return null;
        }
    }
}
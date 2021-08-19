package service;


import dao.LoginDao;
import module.Login;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.Persistence;
import java.util.List;

public class LoginService<T> {
    private LoginDao loginDao;
    private Class<T> entityClass;

    public LoginService() {
        try {
            loginDao = new LoginDao(Persistence.createEntityManagerFactory("default"));
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void addLogin(Login newLogin) {
        loginDao.create(newLogin);
    }

    public void updateLogin(Login updatedLogin) {
        loginDao.update(updatedLogin);
    }

    public void removeLogin(Login removeLogin, int loginId) {
        loginDao.remove(removeLogin, removeLogin.getIdEmployee());
    }

    public List<Login> getAllLogin() {
        return loginDao.findAll();
    }

    public T find(int id) {
        T x = (T) loginDao.find(id);
        return x;
    }

    public List<Login> login(String username, String password) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        try {
            Query loginQuery = session.createSQLQuery("SELECT * FROM Login WHERE userName = :param1 AND password = :param2");
            loginQuery.setParameter("param1", username);
            loginQuery.setParameter("param2", password);
            List<Login> l = loginQuery.list();
            if (l.isEmpty()) {
                return null;
            } else {
                return l;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}



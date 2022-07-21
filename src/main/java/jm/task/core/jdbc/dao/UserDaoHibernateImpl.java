package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    private SessionFactory sessionFactory = getSessionFactory();

    @Override
    public void createUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()) {
            try{
              session.beginTransaction();
              session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (\n" +
                              "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                              "  `name` VARCHAR(45) NOT NULL,\n" +
                              "  `lastName` VARCHAR(45) NOT NULL,\n" +
                              "  `age` INT NOT NULL,\n" +
                              "   PRIMARY KEY (`id`))")
                      .executeUpdate();
              session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()){
            try{
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try(Session session = getSessionFactory().getCurrentSession()){
            try{
                session.beginTransaction();
                session.save(user);
                System.out.println(user.getName() + " was added in DataBase.");
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = getSessionFactory().getCurrentSession()){
            try{
                session.beginTransaction();
                User user = session.get(User.class, id);
                session.remove(user);
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = getSessionFactory().getCurrentSession()){
            try{
                session.beginTransaction();
                list = session.createQuery("select i from User i").getResultList();
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try(Session session = getSessionFactory().getCurrentSession()){
            try{
                session.beginTransaction();
                session.createQuery("delete User").executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

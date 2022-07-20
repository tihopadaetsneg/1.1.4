package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try(Session session = Util.getConnection().getCurrentSession()) {
            try{
              session.beginTransaction();
              String sql = "CREATE TABLE IF NOT EXISTS users " +
                      "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                      "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                      "age TINYINT NOT NULL);";
              session.createSQLQuery(sql).executeUpdate();
              session.getTransaction().commit();

            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try(Session session = Util.getConnection().getCurrentSession()){
            try{
                session.beginTransaction();
                String sql = "drop table if exists users";
                session.createSQLQuery(sql).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = Util.getConnection().getCurrentSession()){
            try{
                session.beginTransaction();
                User user = new User(name, lastName, age);
                session.save(user);
                System.out.println(user.getName() + " был добавлен в базу данных.");
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getConnection().getCurrentSession()){
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
        try(Session session = Util.getConnection().getCurrentSession()){
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
        try(Session session = Util.getConnection().getCurrentSession()){
            try{
                session.beginTransaction();
                String sql = "delete from users";
                session.createSQLQuery(sql).executeUpdate();
                session.getTransaction().commit();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

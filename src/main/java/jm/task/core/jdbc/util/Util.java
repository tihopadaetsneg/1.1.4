package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД
    private static SessionFactory sessionFactory;
    public static SessionFactory getConnection(){
        if (sessionFactory == null){
            try {
                /*
                    Благодаря SessionFactory, а точнее тому, что я в конфигах настроил путь до БД
                    в теге <SessionFactory> - мне не приходится больше нихуя делать кроме как указать конф файл.
                    Его кстати тоже можно не указывать если он назван по умолчанию (так, как назван сейчас)
                 */
                sessionFactory =  new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(User.class) //Тут указываем класс, который имеет специальные аннотации
                        .buildSessionFactory();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}

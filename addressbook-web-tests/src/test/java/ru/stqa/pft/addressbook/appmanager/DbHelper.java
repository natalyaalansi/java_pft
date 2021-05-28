package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where deprecated='0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public Users usersWithoutGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where deprecated='0000-00-00' and groups.size = 0").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData userById(int id) {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery(String.format("from UserData where id = %s", id)).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }

  public Users usersInGroup() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result
            = session.createQuery("from UserData where deprecated = '0000-00-00' and groups.size > 0").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}

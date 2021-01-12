package org.doit.dao;

import org.doit.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoHibernate implements UserDao {

    private final SessionFactory sessionFactory;

    public UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session currentSession() {
        return sessionFactory.openSession();
    }

    @Override
    public List<User> getAll() {
        return currentSession().createQuery("select * from users", User.class).list();
    }

    @Override
    public User getByEmail(String email) {
        Query<User> query = currentSession().createQuery("select * from users where email = :email", User.class);
        query.setParameter("email", email);
        return query.list().stream().findAny().orElse(null);

    }

    @Override
    public void createUser(User user) {
        currentSession().save(user);
    }
}

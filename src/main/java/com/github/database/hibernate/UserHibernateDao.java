package com.github.database.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@SuppressWarnings("unchecked")
public class UserHibernateDao {
    private Session session;

    public UserHibernateDao(Session session) {
        this.session = session;
    }

    public UserHibernate getByLogin(String login) {
        return (UserHibernate) session.createCriteria(UserHibernate.class)
                .add(Restrictions.like("login", login)).uniqueResult();
    }

    public List<UserHibernate> findAll() {
        return session.createCriteria(UserHibernate.class).list();
    }

    public void save(String login, String password) {
        UserHibernate user = new UserHibernate(login, password);
        session.save(user);
    }

    public void deleteById(Long id) {
        session.createQuery("DELETE FROM UserHibernate u WHERE u.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}

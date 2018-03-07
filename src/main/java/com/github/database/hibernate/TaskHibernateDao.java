package com.github.database.hibernate;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.time.LocalDateTime;
import java.util.List;

@SuppressWarnings("unchecked")
public class TaskHibernateDao {
    private Session session;

    public TaskHibernateDao(Session session) {
        this.session = session;
    }

    public TaskHibernate getByName(String name) {
        return (TaskHibernate) session.createCriteria(TaskHibernate.class)
                .add(Restrictions.like("name", name)).uniqueResult();
    }

    public List<TaskHibernate> findAll() {
        return session.createCriteria(TaskHibernate.class).list();
    }

    public void save(String name) {
        TaskHibernate task = new TaskHibernate(name);
        session.save(task);
    }

    public void markAsDone(Long userId, Long taskId) {

        session.createQuery("UPDATE TaskHibernate t SET t.completedBy = :userId, t.completedAt=:now WHERE t.id = :taskId")
                .setParameter("userId", userId)
                .setParameter("taskId", taskId)
                .setParameter("now", LocalDateTime.now())
                .executeUpdate();
    }

    public void removeCompleted() {
        session.createQuery("DELETE FROM TaskHibernate t WHERE t.completedBy IS NOT NULL ").executeUpdate();
    }
}

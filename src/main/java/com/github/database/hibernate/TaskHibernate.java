package com.github.database.hibernate;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "task")
public class TaskHibernate {

    public TaskHibernate() {
    }

    public TaskHibernate(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "completedAt")
    private LocalDateTime compleatedAt;

    @Column(name = "completedBY")
    private LocalDateTime compleatedBY;

    @ManyToMany(mappedBy = "taskList")
    private List<UserHibernate> userList;

    @Override
    public String toString() {
        return "TaskHibernate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", compleatedAt=" + compleatedAt +
                ", compleatedBY=" + compleatedBY +
                ", userList=" + userList +
                '}';
    }
}
package com.github.database.hibernate;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "users")
public class UserHibernate {

    public UserHibernate() {
    }

    public UserHibernate(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToMany(cascade = ALL)
    @JoinColumn(name = "task_id")
    private List<TaskHibernate> taskList;

    //joining tables
    /*@OneToMany(cascade = {ALL}, fetch = LAZY)
    @JoinTable(
            name = "user_task",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id")}
    )
    public List<TaskUserHibernate> projects;*/

    @Override
    public String toString() {
        return "UserHibernate{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", taskList=" + taskList +
                '}';
    }
}

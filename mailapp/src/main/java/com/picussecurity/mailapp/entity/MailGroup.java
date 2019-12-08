package com.picussecurity.mailapp.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Created volkanulutas on 07.12.2019.
 */
@Entity(name = "MailGroup")
@EntityListeners(AuditingEntityListener.class)
public class MailGroup implements Serializable {
    private static final long serialVersionUID = -2970811671439366696L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> userList = new ArrayList<>();

    public MailGroup() {
    }

    public MailGroup(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MailGroup mailGroup = (MailGroup) o;
        return Objects.equals(id, mailGroup.id) &&
                Objects.equals(userList, mailGroup.userList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userList);
    }

    @Override
    public String toString() {
        return "MailGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

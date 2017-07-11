package ru.aryukov.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by oaryukov on 08.06.2017.
 */
@Entity(name = "user")
public class User extends DataSet{

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

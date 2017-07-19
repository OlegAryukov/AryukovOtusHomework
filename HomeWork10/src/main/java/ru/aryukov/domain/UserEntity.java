package ru.aryukov.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dev on 14.07.17.
 */
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
           orphanRemoval = true)//fetch = FetchType.EAGER)
    private List<UserPhoneEntity> userPhoneEntityList = new ArrayList<>();

    public UserEntity() {

    }

    public UserEntity(UserEntity uds) {
        //this.id = uds.getId();
        this.name = uds.getName();
        this.age = uds.getAge();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public List<UserPhoneEntity> getUserPhoneEntityList() {
        return userPhoneEntityList;
    }

    public void setUserPhoneEntityList(List<UserPhoneEntity> userPhoneEntity) {
        this.userPhoneEntityList = userPhoneEntity;
    }

    public void addPhone(UserPhoneEntity userPhoneEntity){
        userPhoneEntityList.add(userPhoneEntity);
        userPhoneEntity.setUser(this);
    }
    public void removePhone(UserPhoneEntity userPhoneEntity){
        userPhoneEntityList.remove(userPhoneEntity);
        userPhoneEntity.setUser(null);
    }


    /*@Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (UserPhoneEntity upe: userPhoneEntityList) {
            result.append(upe.toString());
        }
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", userPhoneEntity=" + result.toString() +
                '}';
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;

        if (userPhoneEntityList != null) {
            if (that.userPhoneEntityList != null) {
                return userPhoneEntityList.size() == that.userPhoneEntityList.size()
                        && userPhoneEntityList.stream().filter(s -> that.userPhoneEntityList.contains(s)).collect(Collectors.toSet()).isEmpty();
            } else {
                return false;
            }
        } else {
            return that.userPhoneEntityList == null;
        }
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (userPhoneEntityList != null ? userPhoneEntityList.hashCode() : 0);
        return result;
    }
}

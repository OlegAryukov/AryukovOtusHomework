package ru.aryukov.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dev on 14.07.17.
 */
@Entity
@Table(name = "user_entity")
public class UserEntity {

    @Id
    //@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private UserAddressEntity userAddressEntity;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
           orphanRemoval = true)//fetch = FetchType.EAGER)
    private List<UserPhoneEntity> userPhoneEntityList = new ArrayList<>();

    public UserEntity() {

    }

    public UserEntity(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.name = userEntity.getName();
        this.age = userEntity.getAge();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        if(userAddressEntity!=null){
            userAddressEntity.setId(id);
        }
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

    public UserAddressEntity getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
        if(userAddressEntity!=null){
            userAddressEntity.setUserEntity(this);
        }
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
        if (!(o instanceof UserEntity)) return false;

        UserEntity that = (UserEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getName().equals(that.getName())) return false;
        if (!getAge().equals(that.getAge())) return false;
       //f (!getUserAddressEntity().equals(that.getUserAddressEntity())) return false;
        return getUserPhoneEntityList().equals(that.getUserPhoneEntityList());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getName().hashCode();
        result = 31 * result + getAge().hashCode();
        result = 31 * result + getUserAddressEntity().hashCode();
        result = 31 * result + getUserPhoneEntityList().hashCode();
        return result;
    }
}

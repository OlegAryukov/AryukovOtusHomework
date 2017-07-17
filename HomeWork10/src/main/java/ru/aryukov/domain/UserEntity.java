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

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="user_address_id")
    private UserAddressEntity userAddressEntity;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserPhoneEntity> phoneDataSet = new ArrayList<>();

    public UserEntity() {

    }

    public UserEntity(UserEntity uds) {
        this.id = uds.getId();
        this.name = uds.getName();
        this.age = uds.getAge();
    }

    public UserAddressEntity getUserAddressEntity() {
        return userAddressEntity;
    }

    public void setUserAddressEntity(UserAddressEntity userAddressEntity) {
        this.userAddressEntity = userAddressEntity;
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

    public List<UserPhoneEntity> getPhoneDataSet() {
        return phoneDataSet;
    }

    public void setPhoneDataSet(List<UserPhoneEntity> phoneDataSet) {
        this.phoneDataSet = phoneDataSet;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", userAddressEntity=" + userAddressEntity +
                ", phoneDataSet=" + phoneDataSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (userAddressEntity != null ? !userAddressEntity.equals(that.userAddressEntity) : that.userAddressEntity != null)
            return false;

        if (phoneDataSet != null) {
            if (that.phoneDataSet != null) {
                return phoneDataSet.size() == that.phoneDataSet.size()
                        && phoneDataSet.stream().filter(s -> that.phoneDataSet.contains(s)).collect(Collectors.toSet()).isEmpty();
            } else {
                return false;
            }
        } else {
            return that.phoneDataSet == null;
        }
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (userAddressEntity != null ? userAddressEntity.hashCode() : 0);
        result = 31 * result + (phoneDataSet != null ? phoneDataSet.hashCode() : 0);
        return result;
    }
}

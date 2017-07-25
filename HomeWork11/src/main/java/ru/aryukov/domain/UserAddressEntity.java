package ru.aryukov.domain;

import javax.persistence.*;

/**
 * Created by dev on 14.07.17.
 */
@Entity
@Table(name = "user_address")
public class UserAddressEntity {
    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "street")
    private String street;

    @Column(name = "index")
    private Integer index;

    @OneToOne
    @MapsId
    private UserEntity userEntity;

    public UserAddressEntity() {
    }

    public UserAddressEntity(UserAddressEntity addressEntity) {
        this.id = addressEntity.getId();
        this.street = addressEntity.getStreet();
        this.index = addressEntity.getIndex();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", index=" + index +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAddressEntity)) return false;

        UserAddressEntity that = (UserAddressEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getStreet().equals(that.getStreet())) return false;
        return getUserEntity().equals(that.getUserEntity());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getStreet().hashCode();
        result = 31 * result + getUserEntity().hashCode();
        return result;
    }
}

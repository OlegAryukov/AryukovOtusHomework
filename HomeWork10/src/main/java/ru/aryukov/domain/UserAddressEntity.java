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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "street")
    private String street;

    @Column(name = "index")
    private int index;

    public UserAddressEntity() {
    }

    public UserAddressEntity(UserAddressEntity ads) {
        this.id = ads.getId();
        this.street = ads.getStreet();
        this.index = ads.getIndex();
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
        if (o == null || getClass() != o.getClass()) return false;

        UserAddressEntity that = (UserAddressEntity) o;

        if (index != that.index) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return street != null ? street.equals(that.street) : that.street == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + index;
        return result;
    }
}

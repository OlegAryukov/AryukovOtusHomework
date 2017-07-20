package ru.aryukov.domain;

import javax.persistence.*;

/**
 * Created by dev on 14.07.17.
 */
@Entity
@Table(name = "user_phones")
public class UserPhoneEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "number")
    private String number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public UserPhoneEntity() {

    }

    public UserPhoneEntity(UserPhoneEntity phoneEntity) {
        this.id = phoneEntity.getId();
        this.code = phoneEntity.getCode();
        this.number = phoneEntity.getNumber();
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "UserPhoneEntityDao {" +
                "id=" + id +
                ", code=" + code +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserPhoneEntity)) return false;

        UserPhoneEntity that = (UserPhoneEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getCode().equals(that.getCode())) return false;
        if (!getNumber().equals(that.getNumber())) return false;
        return getUser().equals(that.getUser());
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getCode().hashCode();
        result = 31 * result + getNumber().hashCode();
        result = 31 * result + getUser().hashCode();
        return result;
    }
}

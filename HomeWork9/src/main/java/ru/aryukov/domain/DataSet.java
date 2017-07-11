package ru.aryukov.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by oaryukov on 04.07.2017.
 */
@Entity
public abstract class DataSet {
    @Id
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

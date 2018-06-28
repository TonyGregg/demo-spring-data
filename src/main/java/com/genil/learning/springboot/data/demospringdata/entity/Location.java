package com.genil.learning.springboot.data.demospringdata.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by genil on 6/28/18 at 04 37
 **/
@Entity
public class Location {
    @Id
    @GeneratedValue
    private Long id;

    public Location(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}

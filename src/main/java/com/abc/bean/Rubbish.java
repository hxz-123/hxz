package com.abc.bean;

import lombok.Data;

import javax.persistence.Id;

@Data
public class Rubbish {
    @Id
    private String id;
    private String name;
    private int category_id;
    private int category_grandpa_id;

    public Rubbish(String id, String name, int category_id, int category_grandpa_id) {
        this.id = id;
        this.name = name;
        this.category_id = category_id;
        this.category_grandpa_id = category_grandpa_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCategory_grandpa_id() {
        return category_grandpa_id;
    }

    public void setCategory_grandpa_id(int category_grandpa_id) {
        this.category_grandpa_id = category_grandpa_id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

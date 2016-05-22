package com.freetmp.investigate.persistence.querydsl;

import javax.annotation.Generated;

/**
 * UradioService is a Querydsl bean drinkType
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class UradioService {

    private Integer id;

    private String name;

    private String prefix;

    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
         return "id = " + id + ", name = " + name + ", prefix = " + prefix + ", url = " + url;
    }

}


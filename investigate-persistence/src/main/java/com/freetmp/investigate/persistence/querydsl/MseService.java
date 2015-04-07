package com.freetmp.investigate.persistence.querydsl;

import javax.annotation.Generated;

/**
 * MseService is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class MseService {

    private Integer id;

    private String password;

    private String url;

    private String username;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
         return "id = " + id + ", password = " + password + ", url = " + url + ", username = " + username;
    }

}


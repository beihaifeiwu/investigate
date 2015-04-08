/**                                                                          
 * Copyright 2015-2015 the original author or authors.                         
 *                                                                           
 *       HaHa,I have the right to do anything!                               
 */
package com.freetmp.xmbg.mysql.entity;

import java.io.Serializable;

public class Task implements Serializable {

    private Long id;

    private String title;

    private String description;

    private Long userId;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new  StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", description=").append(description);
        sb.append(", userId=").append(userId);
        sb.append("]");
        return sb.toString();
    }
}

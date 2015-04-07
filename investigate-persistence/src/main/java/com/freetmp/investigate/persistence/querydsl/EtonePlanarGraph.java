package com.freetmp.investigate.persistence.querydsl;

import javax.annotation.Generated;

/**
 * EtonePlanarGraph is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class EtonePlanarGraph {

    private Boolean durable;

    private String floor;

    private Double originX;

    private Double originY;

    private Long planarGraphId;

    public Boolean getDurable() {
        return durable;
    }

    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Double getOriginX() {
        return originX;
    }

    public void setOriginX(Double originX) {
        this.originX = originX;
    }

    public Double getOriginY() {
        return originY;
    }

    public void setOriginY(Double originY) {
        this.originY = originY;
    }

    public Long getPlanarGraphId() {
        return planarGraphId;
    }

    public void setPlanarGraphId(Long planarGraphId) {
        this.planarGraphId = planarGraphId;
    }

    @Override
    public String toString() {
         return "durable = " + durable + ", floor = " + floor + ", originX = " + originX + ", originY = " + originY + ", planarGraphId = " + planarGraphId;
    }

}


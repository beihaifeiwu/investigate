package com.freetmp.investigate.persistence.querydsl;

import javax.annotation.Generated;

/**
 * MsePlanarGraph is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class MsePlanarGraph {

    private Boolean durable;

    private Long floorRefId;

    private Long id;

    private Double originX;

    private Double originY;

    private Long planarGraphId;

    private Integer serviceId;

    public Boolean getDurable() {
        return durable;
    }

    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    public Long getFloorRefId() {
        return floorRefId;
    }

    public void setFloorRefId(Long floorRefId) {
        this.floorRefId = floorRefId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String toString() {
         return "durable = " + durable + ", floorRefId = " + floorRefId + ", id = " + id + ", originX = " + originX + ", originY = " + originY + ", planarGraphId = " + planarGraphId + ", serviceId = " + serviceId;
    }

}


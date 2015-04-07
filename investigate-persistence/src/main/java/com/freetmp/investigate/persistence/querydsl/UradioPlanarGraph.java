package com.freetmp.investigate.persistence.querydsl;

import javax.annotation.Generated;

/**
 * UradioPlanarGraph is a Querydsl bean type
 */
@Generated("com.mysema.query.codegen.BeanSerializer")
public class UradioPlanarGraph {

    private Double deltaX;

    private Double deltaY;

    private Integer dpi;

    private Boolean durable;

    private String id;

    private Integer mapHeight;

    private Long mapId;

    private Integer mapWidth;

    private Double originX;

    private Double originY;

    private Float scale;

    private Float uradioScale;

    public Double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(Double deltaX) {
        this.deltaX = deltaX;
    }

    public Double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(Double deltaY) {
        this.deltaY = deltaY;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public Boolean getDurable() {
        return durable;
    }

    public void setDurable(Boolean durable) {
        this.durable = durable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMapHeight() {
        return mapHeight;
    }

    public void setMapHeight(Integer mapHeight) {
        this.mapHeight = mapHeight;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public Integer getMapWidth() {
        return mapWidth;
    }

    public void setMapWidth(Integer mapWidth) {
        this.mapWidth = mapWidth;
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

    public Float getScale() {
        return scale;
    }

    public void setScale(Float scale) {
        this.scale = scale;
    }

    public Float getUradioScale() {
        return uradioScale;
    }

    public void setUradioScale(Float uradioScale) {
        this.uradioScale = uradioScale;
    }

    @Override
    public String toString() {
         return "deltaX = " + deltaX + ", deltaY = " + deltaY + ", dpi = " + dpi + ", durable = " + durable + ", id = " + id + ", mapHeight = " + mapHeight + ", mapId = " + mapId + ", mapWidth = " + mapWidth + ", originX = " + originX + ", originY = " + originY + ", scale = " + scale + ", uradioScale = " + uradioScale;
    }

}


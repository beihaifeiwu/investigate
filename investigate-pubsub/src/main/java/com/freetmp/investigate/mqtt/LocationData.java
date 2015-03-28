package com.freetmp.investigate.mqtt;

/**
 * Created by LiuPin on 2015/3/24.
 */
public class LocationData {

    private String idType;

    private String idData;

    private Long mapId;

    private int expiresIn;

    private Long timestamp;

    private double x;

    private double y;

    private String status;

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public Long getMapId() {
        return mapId;
    }

    public void setMapId(Long mapId) {
        this.mapId = mapId;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "idType='" + idType + '\'' +
                ", idData='" + idData + '\'' +
                ", mapId=" + mapId +
                ", expiresIn=" + expiresIn +
                ", timestamp=" + timestamp +
                ", x=" + x +
                ", y=" + y +
                ", status='" + status + '\'' +
                '}';
    }
}

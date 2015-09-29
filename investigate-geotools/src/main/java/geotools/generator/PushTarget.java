package geotools.generator;

import java.util.Random;

/**
 * hold the information about the push traget
 *
 * @author Pin Liu
 */
public class PushTarget {

  private String ip;

  private int port;

  private Long mapId;

  private String floor;

  private String mac;

  /**
   * 向目标推送的频率
   */
  private int frequency;

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public Long getMapId() {
    return mapId;
  }

  public void setMapId(Long mapId) {
    this.mapId = mapId;
  }

  public int getFrequency() {
    return frequency;
  }

  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  public String getFloor() {
    return floor;
  }

  public void setFloor(String floor) {
    this.floor = floor;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  private String randomMACAddress() {
    Random rand = new Random();
    byte[] macAddr = new byte[6];
    rand.nextBytes(macAddr);

    macAddr[0] = (byte) (macAddr[0] & (byte) 254); // zeroing last 2 bytes to
    // make it unicast and
    // locally adminstrated

    StringBuilder sb = new StringBuilder(18);
    for (byte b : macAddr) {
      if (sb.length() > 0)
        sb.append(":");

      sb.append(String.format("%02x", b));
    }
    return sb.toString();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    PushTarget target = new PushTarget();
    target.setFloor(floor);
    target.setFrequency(frequency);
    target.setIp(ip);
    target.setMapId(mapId);
    target.setPort(port);
    target.setMac(randomMACAddress());
    return target;
  }
}
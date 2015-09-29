package geotools.generator;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * the runnable task for the executing of the model recording
 *
 * @author Pin Liu
 */
class DefaultPushTask implements PushTask {

  private final PushTarget target;

  private Timer timer = new Timer();

  private final List<Point> points;

  private int index = 0;

  private boolean isRunning = false;

  private Generator generator;

  public DefaultPushTask(PushTarget target, List<Point> points, Generator generator) throws SocketException {
    this.target = target;
    this.points = points;
  }

  public DefaultPushTask(PushTarget target, List<Point> points, int startIndex, Generator generator) throws SocketException {
    this(target, points, generator);
    this.index = startIndex;
  }

  @Override
  public void run() {
    Generator.executor.execute(this::timeShedule);
  }

  protected void timeShedule() {
    timer.scheduleAtFixedRate(new TimerTask() {
                                @Override
                                public void run() {
                                  if (index < points.size()) {
                                    push(points.get(index++));
                                    isRunning = true;
                                  } else {
                                    checkToRunOrStop();
                                  }
                                }
                              }, new Random(System.currentTimeMillis()).nextInt(target.getFrequency() * 1000),
        target.getFrequency() == 0 ? 1000 : target.getFrequency() * 1000);
  }

  void push(Point point) {
    byte[] buf = buildBuf(point);
    DatagramPacket packet = new DatagramPacket(buf, buf.length);
    packet.setSocketAddress(new InetSocketAddress(target.getIp(), target.getPort()));
    try {
      generator.send(packet);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      generator.highLightThePoint(point, target.getMac());
    }
  }

  byte[] buildBuf(Point point) {

    Phone.Result.Builder builder = Phone.Result.newBuilder();
    builder.setFloorId(Integer.parseInt(target.getFloor()));
    builder.setPhoneMac(target.getMac());
    builder.setPositionX((float) (point.x / 10 + 13526590.33017226));
    builder.setPositionY((float) (-point.y / 10 + 3663422.983396762));
    builder.setScene(191);
    builder.setStamp(new Long(System.currentTimeMillis()).intValue());

/*      StringBuilder sb = new StringBuilder();
      String mac = target.getMac();
      if(mac != null) mac = mac.replaceAll(":", "");
      sb.append(mac).append(",");
      sb.append(point.x * 4).append(",");
      sb.append(-point.y * 4).append(",");
      sb.append(target.getFloor());*/
    return builder.build().toByteArray();
  }

  public void checkToRunOrStop() {
    if (generator.isLoopBroadcast()) {
      index = 0;
      isRunning = true;
    } else {
      stop();
    }
  }

  public boolean isRunning() {
    return isRunning;
  }

  public void pause() {
    stop();
  }

  public void stop() {
    timer.cancel();
    isRunning = false;
    timer = new Timer();
  }
}
  